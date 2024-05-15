package com.project.eventxperience.controller;

import com.project.eventxperience.model.Rating;
import com.project.eventxperience.model.User;
import com.project.eventxperience.model.dto.RatingDTO;
import com.project.eventxperience.service.RatingService;
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
    public ResponseEntity<Rating> addRating(@RequestBody RatingDTO ratingDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        ratingDTO.setUserId(currentUser.getId());

        Rating createdRating = ratingService.saveRating(ratingDTO);

        return ResponseEntity.ok(createdRating);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Rating> deleteRating(@PathVariable Long id) {
        Rating ratingDeleted = ratingService.deleteById(id);

        return ResponseEntity.ok(ratingDeleted);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Iterable<Rating>> getAllRatingsByUserId(@PathVariable Long userId) {
        Iterable<Rating> ratings = ratingService.findAllByUserId(userId);

        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/attraction/{attractionId}")
    public ResponseEntity<Iterable<Rating>> getAllRatingsByAttractionId(@PathVariable Long attractionId) {
        Iterable<Rating> ratings = ratingService.findAllByAttractionId(attractionId);

        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<Iterable<Rating>> getAllRatingsByEventId(@PathVariable Long eventId) {
        Iterable<Rating> ratings = ratingService.findAllByEventId(eventId);

        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Rating>> getRatingById(@PathVariable Long id) {
        Optional<Rating> rating = ratingService.findById(id);

        return ResponseEntity.ok(rating);
    }
}
