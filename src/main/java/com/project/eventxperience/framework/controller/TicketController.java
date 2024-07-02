package com.project.eventxperience.framework.controller;

import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.service.TicketService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    @PostMapping("/purchase/{eventId}")
    public ResponseEntity<?> purchaseTicket(@PathVariable Long eventId) {
        try {
            User currentUser = getCurrentUser();
            return ResponseEntity.ok(ticketService.purchaseTicket(currentUser, eventId));
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/confirmAttendance/{eventId}")
    public ResponseEntity<?> confirmAttendance(@PathVariable Long eventId, Authentication authentication) {
        try {
            User currentUser = (User) authentication.getPrincipal();

            Long userId = currentUser.getId();

            ticketService.confirmAttendance(eventId, userId);
            return ResponseEntity.ok("Presença confirmada com sucesso.");
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
