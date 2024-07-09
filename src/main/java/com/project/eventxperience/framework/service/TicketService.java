package com.project.eventxperience.framework.service;

import com.project.eventxperience.framework.model.Event;
import com.project.eventxperience.framework.model.Ticket;
import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.repository.TicketRepository;
import com.project.eventxperience.framework.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketService {
    private final EventRepository<Event> eventRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(EventRepository<Event> eventRepository, TicketRepository ticketRepository) {
        this.eventRepository = eventRepository;
        this.ticketRepository = ticketRepository;
    }

    @Transactional
    public Ticket purchaseTicket(User user, Long eventId) {
        try {
            Event existingEvent = eventRepository.findById(eventId)
                    .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado com id: " + eventId));

            // Verifica se o usuário já possui um ticket para este evento
            boolean hasTicket = user.getTickets().stream()
                    .anyMatch(ticket -> ticket.getEvent().getId().equals(existingEvent.getId()));

            if (hasTicket) {
                throw new IllegalArgumentException("O usuário já possui um ticket para este evento.");
            }

            Ticket ticket = new Ticket();
            ticket.setPrice(existingEvent.getTicketPrice());
            ticket.setUser(user);
            ticket.setEvent(existingEvent);
            ticket.setConfirmed(false);

            return ticketRepository.save(ticket);
        } catch (EntityNotFoundException e) {
            throw e; // Propaga a exceção EntityNotFoundException para ser tratada no controlador
        } catch (IllegalArgumentException e) {
            throw e; // Propaga a exceção IllegalArgumentException para ser tratada no controlador
        }
    }

    @Transactional
    public void confirmAttendance(Long eventId, Long userId) {
        try {
            Event existingEvent = eventRepository.findById(eventId)
                    .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado com id: " + eventId));

            Ticket ticket = ticketRepository.findByEventIdAndUserId(eventId, userId);

            if (ticket == null) {
                throw new EntityNotFoundException("Ticket não encontrado.");
            }

            if (ticket.isConfirmed()) {
                throw new IllegalArgumentException("A presença no evento já foi confirmada.");
            }

            ticket.setConfirmed(true);
            ticketRepository.save(ticket);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao confirmar presença no evento", e);
        }
    }

    @Transactional(readOnly = true)
    public boolean hasConfirmedAttendance(Long eventId, Long userId) {
        Ticket ticket = ticketRepository.findByEventIdAndUserId(eventId, userId);
        return ticket != null && ticket.isConfirmed();
    }

    @Transactional(readOnly = true)
    public int countConfirmedUsersForEvent(Long eventId) {
        return ticketRepository.countByEventIdAndConfirmedTrue(eventId);
    }
}