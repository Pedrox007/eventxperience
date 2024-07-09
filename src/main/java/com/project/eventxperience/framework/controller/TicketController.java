package com.project.eventxperience.framework.controller;

import com.project.eventxperience.framework.model.Event;
import com.project.eventxperience.framework.model.Ticket;
import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.model.dto.TicketDTO;
import com.project.eventxperience.framework.repository.EventRepository;
import com.project.eventxperience.framework.utils.EventUtils;
import com.project.eventxperience.framework.service.TicketService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final EventRepository eventRepository;


    @Autowired
    public TicketController(TicketService ticketService, EventRepository eventRepository) {
        this.ticketService = ticketService;
        this.eventRepository = eventRepository;
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    @PostMapping("/purchase/{eventId}")
    public ResponseEntity<?> purchaseTicket(@PathVariable Long eventId) {
        try {
            User currentUser = getCurrentUser();
            Ticket ticket = ticketService.purchaseTicket(currentUser, eventId);
            TicketDTO ticketDTO = new TicketDTO();
            ticketDTO.parseToDTO(ticket);

            return ResponseEntity.ok(ticketDTO);
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/confirmAttendance/{eventId}/{userId}")
    public ResponseEntity<?> confirmAttendance(@PathVariable Long eventId, @PathVariable Long userId, Authentication authentication) {
        try {
            User currentUser = (User) authentication.getPrincipal();

            Optional<Event> eventOptional = eventRepository.findById(eventId);
            if (eventOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Event event = eventOptional.get();

            if (EventUtils.isOrganizerOfEvent(event, currentUser)) {
                ticketService.confirmAttendance(eventId, userId);
                return ResponseEntity.ok("Presença confirmada com sucesso.");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuário não autorizado a confirmar presença.");
            }
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/hasConfirmedAttendance/{eventId}")
    public ResponseEntity<?> hasConfirmedAttendance(@PathVariable Long eventId, Authentication authentication) {
        try {
            User currentUser = (User) authentication.getPrincipal();

            Long userId = currentUser.getId();

            boolean hasConfirmed = ticketService.hasConfirmedAttendance(eventId, userId);
            return ResponseEntity.ok(hasConfirmed);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/countConfirmedUsersForEvent/{eventId}")
    public ResponseEntity<?> countConfirmedUsersForEvent(@PathVariable Long eventId) {
        try {
            int count = ticketService.countConfirmedUsersForEvent(eventId);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
