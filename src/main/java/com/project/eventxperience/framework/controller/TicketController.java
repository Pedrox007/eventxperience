package com.project.eventxperience.framework.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
public class TicketController {

//    private final TicketService ticketService;
//    private final EventServiceInterface eventService;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    public TicketController(TicketService ticketService, EventServiceInterface eventService) {
//        this.ticketService = ticketService;
//        this.eventService = eventService;
//    }
//
//    @PostMapping("/purchase/{eventId}")
//    public ResponseEntity<Ticket> purchaseTicket(@PathVariable Long eventId, Authentication authentication) {
//        User currentUser = (User) authentication.getPrincipal();
//
//        SportEvent sportEvent;
//
//        try {
//            sportEvent = eventService.retrieveEventById(eventId);
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Ticket boughtTicket = ticketService.purchaseTicket(currentUser, sportEvent);
//        return ResponseEntity.status(HttpStatus.CREATED).body(boughtTicket);
//    }
//
//    @PostMapping("/confirmAttendance/{eventId}/{userId}")
//    public ResponseEntity<String> confirmAttendance(@PathVariable Long eventId, @PathVariable Long userId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User organizer = (User) authentication.getPrincipal();
//
//        SportEvent sportEvent = sportEventService.findById(eventId);
//
//        if (ticketService.hasConfirmedAttendance(sportEvent, userId)) {
//            return ResponseEntity.badRequest().body("Usuário já foi confirmado neste evento.");
//        }
//
//        ticketService.confirmAttendance(sportEvent, userId, organizer);
//        userService.addPointsToUser(userId, 10);
//
//        return ResponseEntity.ok("Presença confirmada com sucesso.O usuário foi recompensado com 5 pontos.");
//
//    }
}
