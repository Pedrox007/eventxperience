package com.project.eventxperience.controller;

import com.project.eventxperience.model.SportEvent;
import com.project.eventxperience.model.Ticket;
import com.project.eventxperience.model.User;
import com.project.eventxperience.service.SportEventService;
import com.project.eventxperience.service.TicketService;
import com.project.eventxperience.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final SportEventService sportEventService;

    @Autowired
    private UserService userService;

    @Autowired
    public TicketController(TicketService ticketService, SportEventService sportEventService) {
        this.ticketService = ticketService;
        this.sportEventService = sportEventService;
    }

    @PostMapping("/purchase/{eventId}")
    public ResponseEntity<Ticket> purchaseTicket(@PathVariable Long eventId, Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();

        SportEvent sportEvent;

        try {
            sportEvent = sportEventService.findById(eventId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        Ticket boughtTicket = ticketService.purchaseTicket(currentUser, sportEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(boughtTicket);
    }

    @PostMapping("/confirmAttendance/{eventId}/{userId}")
    public ResponseEntity<String> confirmAttendance(@PathVariable Long eventId, @PathVariable Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User organizer = (User) authentication.getPrincipal();

        try {
            SportEvent sportEvent = sportEventService.findById(eventId);

            if (ticketService.hasConfirmedAttendance(sportEvent, userId)) {
                return ResponseEntity.badRequest().body("Usuário já foi confirmado neste evento.");
            }

            ticketService.confirmAttendance(sportEvent, userId, organizer);
            userService.addPointsToUser(userId, 5);

            return ResponseEntity.ok("Presença confirmada com sucesso.O usuário foi recompensado com 5 pontos.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
