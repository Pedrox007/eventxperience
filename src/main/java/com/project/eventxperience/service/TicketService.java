package com.project.eventxperience.service;

import com.project.eventxperience.model.Role;
import com.project.eventxperience.model.SportEvent;
import com.project.eventxperience.model.Ticket;
import com.project.eventxperience.model.User;
import com.project.eventxperience.repository.SportEventRepository;
import com.project.eventxperience.repository.TicketRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        // Verifica se o usuário já possui um ticket para este evento
        boolean hasTicket = user.getTickets().stream()
                .anyMatch(ticket -> ticket.getSportEvent().getId().equals(sportEvent.getId()));

        if (hasTicket) {
            throw new IllegalArgumentException("O usuário já possui um ticket para este evento.");
        }

        // Criar um novo ticket
        Ticket ticket = new Ticket();
        ticket.setPrice(sportEvent.getTicketPrice());
        ticket.setUser(user);
        ticket.setSportEvent(sportEvent);
        ticket.setConfirmed(false);

        return ticketRepository.save(ticket);
    }

    public void confirmAttendance(SportEvent sportEvent, Long userId, User organizer) {
        if (sportEvent == null || sportEvent.getCreator() == null) {
            throw new IllegalArgumentException("O evento esportivo ou o criador do evento não foi encontrado.");
        }

        // Verificar se o usuário organizador tem permissão para confirmar a presença
        System.out.println(isOrganizerOfEvent(sportEvent, organizer));
        if (!isOrganizerOfEvent(sportEvent, organizer)) {
            throw new IllegalArgumentException("Você não tem permissão para confirmar a presença neste evento.");
        }

        // Obter o ticket correspondente ao evento e userId
        Ticket ticket = ticketRepository.findBySportEventIdAndUserId(sportEvent.getId(), userId);

        // Verificar se o ticket existe
        if (ticket == null) {
            throw new EntityNotFoundException("Ticket não encontrado.");
        }
        
        // Atualizar o status do ticket para confirmado
        ticket.setConfirmed(true);
        ticketRepository.save(ticket);
    }

    private boolean isOrganizerOfEvent(SportEvent event, User user) {
        List<Role> roles = user.getRoles();

        if (event.getCreator() != null && event.getCreator().getId().equals(user.getId())) {
            for (Role role : roles) {
                if ("ROLE_ORGANIZER".equals(role.getName())) {
                    System.out.println("- Usuário é um organizador");
                    return true;
                }
            }
        }

        return false;
    }
}