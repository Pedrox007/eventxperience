package com.project.eventxperience.controller;

import com.project.eventxperience.model.Sport;
import com.project.eventxperience.model.SportEvent;
import com.project.eventxperience.model.User;
import com.project.eventxperience.model.dto.SportEventDTO;
import com.project.eventxperience.service.SportEventService;
import com.project.eventxperience.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sport_events")
public class SportEventController {

    private final SportEventService sportEventService;

    @Autowired
    public SportEventController(SportEventService sportEventService) {
        this.sportEventService = sportEventService;
    }

    @PostMapping
    public ResponseEntity<SportEvent> addSportEvent(@RequestBody SportEventDTO sportEventDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        sportEventDTO.setCreator_id(currentUser.getId());
        SportEvent createdSportEvent = sportEventService.addSportEvent(sportEventDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdSportEvent);
    }
}