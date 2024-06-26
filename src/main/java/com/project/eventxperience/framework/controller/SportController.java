package com.project.eventxperience.framework.controller;

import com.project.eventxperience.framework.service.SportService;
import com.project.eventxperience.sportevent.model.Sport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sports")
public class SportController {

    private final SportService sportService;

    @Autowired
    public SportController(SportService sportService) {
        this.sportService = sportService;
    }

    @GetMapping
    public ResponseEntity<List<Sport>> getAllSports() {
        List<Sport> sports = sportService.getAllSports();
        return ResponseEntity.ok().body(sports);
    }

    @PostMapping
    public ResponseEntity<Sport> addSport(@RequestBody Sport sport) {
        Sport createdSport = sportService.addSport(sport);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSport);
    }
}