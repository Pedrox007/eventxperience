package com.project.eventxperience.controller;

import com.project.eventxperience.model.Attraction;
import com.project.eventxperience.model.Sport;
import com.project.eventxperience.model.SportEvent;
import com.project.eventxperience.model.User;
import com.project.eventxperience.model.dto.AttractionDTO;
import com.project.eventxperience.model.dto.SportEventDTO;
import com.project.eventxperience.service.AttractionService;
import com.project.eventxperience.service.SportEventService;
import com.project.eventxperience.service.SportService;
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

    @Autowired
    public SportEventController(SportEventService sportEventService, AttractionService attractionService) {
        this.sportEventService = sportEventService;
        this.attractionService = attractionService;
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

    @PostMapping("/{id}/save_attractions")
    public ResponseEntity<List<Attraction>> saveAttractions(@PathVariable Long id, @RequestBody List<AttractionDTO> attractionsDTO) {
        List<Attraction> attractions = attractionService.saveAttractions(id, attractionsDTO);
        return ResponseEntity.ok(attractions);
    }
}