package com.project.eventxperience.framework.service;

import com.project.eventxperience.framework.sportevent.model.SportEvent;
import com.project.eventxperience.framework.model.Ticket;
import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.repository.SportEventRepository;
import com.project.eventxperience.framework.repository.TicketRepository;
import com.project.eventxperience.framework.utils.EventUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final SportEventRepository sportEventRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, SportEventRepository sportEventRepository) {
        this.ticketRepository = ticketRepository;
        this.sportEventRepository = sportEventRepository;
    }

    public Ticket purchaseTicket(User user, SportEvent sportEvent) {
        try {
            // Verifica se o usuário já possui um ticket para este evento
            boolean hasTicket = user.getTickets().stream()
                    .anyMatch(ticket -> ticket.getSportEvent().getId().equals(sportEvent.getId()));

            if (hasTicket) {
                throw new IllegalArgumentException("O usuário já possui um ticket para este evento.");
            }

            Ticket ticket = new Ticket();
            ticket.setPrice(sportEvent.getTicketPrice());
            ticket.setUser(user);
            ticket.setSportEvent(sportEvent);
            ticket.setConfirmed(false);

            return ticketRepository.save(ticket);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao obter o ticket", e);
        }
    }

    public void confirmAttendance(SportEvent sportEvent, Long userId, User organizer) {
        try {
            if (sportEvent == null || sportEvent.getCreator() == null) {
                throw new IllegalArgumentException("O evento esportivo ou o criador do evento não foi encontrado.");
            }

            if (!EventUtils.isOrganizerOfEvent(sportEvent, organizer)) {
                throw new IllegalArgumentException("Você não tem permissão para confirmar a presença neste evento.");
            }

            Ticket ticket = ticketRepository.findBySportEventIdAndUserId(sportEvent.getId(), userId);

            if (ticket == null) {
                throw new EntityNotFoundException("Ticket não encontrado.");
            }

            ticket.setConfirmed(true);
            ticketRepository.save(ticket);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao confirmar presença no evento", e);
        }
    }

    public boolean hasConfirmedAttendance(SportEvent sportEvent, Long userId) {
        Ticket ticket = ticketRepository.findBySportEventIdAndUserId(sportEvent.getId(), userId);
        return ticket != null && ticket.isConfirmed();
    }

    public int countConfirmedUsersForEvent(Long eventId) {
        return ticketRepository.countBySportEventIdAndConfirmedTrue(eventId);
    }
}