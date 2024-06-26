package com.project.eventxperience.framework.controller;

import com.project.eventxperience.framework.model.Attraction;
import com.project.eventxperience.framework.sportevent.model.SportEvent;
import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.model.dto.AttractionDTO;
import com.project.eventxperience.framework.model.dto.SportEventDTO;
import com.project.eventxperience.framework.service.AttractionService;
import com.project.eventxperience.framework.service.SportEventService;
import com.project.eventxperience.framework.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sport_events")
public class SportEventController {

    private final SportEventService sportEventService;
    private final AttractionService attractionService;

    private final TicketService ticketService;


    @Autowired
    public SportEventController(SportEventService sportEventService, AttractionService attractionService, TicketService ticketService) {
        this.sportEventService = sportEventService;
        this.attractionService = attractionService;
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<SportEvent> addSportEvent(@RequestBody SportEventDTO sportEventDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        sportEventDTO.setCreator_id(currentUser.getId());
        SportEvent createdSportEvent = sportEventService.addSportEvent(sportEventDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdSportEvent);
    }

    @GetMapping
    public ResponseEntity<Iterable<SportEvent>> getSportEvents() {
        Iterable<SportEvent> sportEvents = sportEventService.getSportEvents();
        return ResponseEntity.ok(sportEvents);
    }

    @GetMapping("/{eventId}/participants")
    public ResponseEntity<List<User>> getAllParticipantsByEventId(@PathVariable Long eventId) {
        List<User> participants = sportEventService.getAllParticipantsByEventId(eventId);
        return ResponseEntity.ok(participants);

    }

    @PostMapping("/{eventId}/save_attractions")
    public ResponseEntity<List<Attraction>> saveAttractions(@PathVariable Long eventId, @RequestBody List<AttractionDTO> attractionsDTO) {
        List<Attraction> attractions = attractionService.saveAttractions(eventId, attractionsDTO);
        return ResponseEntity.ok(attractions);
    }

    @DeleteMapping("/{eventId}/delete_attractions")
    public ResponseEntity<List<Attraction>> deleteAttractions(@PathVariable Long eventId, @RequestBody List<Long> attractionsIds) {
        List<Attraction> attractions = attractionService.deleteAttractions(eventId, attractionsIds);

        return ResponseEntity.ok(attractions);
    }

    @GetMapping("/{eventId}/total")
    public ResponseEntity<Integer> totalConfirmedUsersForEvent(@PathVariable Long eventId) {
        int totalConfirmedUsers = ticketService.countConfirmedUsersForEvent(eventId);
        return ResponseEntity.ok(totalConfirmedUsers);
    }
}