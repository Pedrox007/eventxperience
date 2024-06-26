package com.project.eventxperience.framework.controller;

import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.model.dto.RatingDTO;
import com.project.eventxperience.framework.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    private RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<com.project.eventxperience.model.Review> addRating(@RequestBody RatingDTO ratingDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        ratingDTO.setUserId(currentUser.getId());

        com.project.eventxperience.model.Review createdRating = ratingService.saveRating(ratingDTO);

        return ResponseEntity.ok(createdRating);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<com.project.eventxperience.model.Review> deleteRating(@PathVariable Long id) {
        com.project.eventxperience.model.Review ratingDeleted = ratingService.deleteById(id);

        return ResponseEntity.ok(ratingDeleted);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Iterable<com.project.eventxperience.model.Review>> getAllRatingsByUserId(@PathVariable Long userId) {
        Iterable<com.project.eventxperience.model.Review> ratings = ratingService.findAllByUserId(userId);

        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/attraction/{attractionId}")
    public ResponseEntity<Iterable<com.project.eventxperience.model.Review>> getAllRatingsByAttractionId(@PathVariable Long attractionId) {
        Iterable<com.project.eventxperience.model.Review> ratings = ratingService.findAllByAttractionId(attractionId);

        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<Iterable<com.project.eventxperience.model.Review>> getAllRatingsByEventId(@PathVariable Long eventId) {
        Iterable<com.project.eventxperience.model.Review> ratings = ratingService.findAllByEventId(eventId);

        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<com.project.eventxperience.model.Review>> getRatingById(@PathVariable Long id) {
        Optional<com.project.eventxperience.model.Review> rating = ratingService.findById(id);

        return ResponseEntity.ok(rating);
    }
}
