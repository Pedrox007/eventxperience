package com.project.eventxperience.controller;

import com.project.eventxperience.model.Sport;
import com.project.eventxperience.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sports")
public class SportController {

    private final SportService sportService;

    @Autowired
    public SportController(SportService sportService) {
        this.sportService = sportService;
    }

    @PostMapping
    public ResponseEntity<Sport> addSport(@RequestBody Sport sport) {
        Sport createdSport = sportService.addSport(sport);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSport);
    }
}