package com.project.eventxperience.framework.controller;

import com.project.eventxperience.framework.model.Review;
import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.model.dto.RatingDTO;
import com.project.eventxperience.framework.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public abstract class ReviewController {
    private ReviewService ratingService;

    @Autowired
    public ReviewController(ReviewService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<Review> addRating(@RequestBody RatingDTO ratingDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        ratingDTO.setUserId(currentUser.getId());

        Review createdRating = ratingService.saveRating(ratingDTO);

        return ResponseEntity.ok(createdRating);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Review> deleteRating(@PathVariable Long id) {
        Review ratingDeleted = ratingService.deleteById(id);

        return ResponseEntity.ok(ratingDeleted);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Iterable<Review>> getAllRatingsByUserId(@PathVariable Long userId) {
        Iterable<Review> ratings = ratingService.findAllByUserId(userId);

        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/attraction/{attractionId}")
    public ResponseEntity<Iterable<Review>> getAllRatingsByAttractionId(@PathVariable Long attractionId) {
        Iterable<Review> ratings = ratingService.findAllByAttractionId(attractionId);

        return ResponseEntity.ok(ratings);
    }

//    @GetMapping("/event/{eventId}")
//    public ResponseEntity<Iterable<Review>> getAllRatingsByEventId(@PathVariable Long eventId) {
//        Iterable<Review> ratings = ratingService.findAllByEventId(eventId);
//
//        return ResponseEntity.ok(ratings);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Review>> getRatingById(@PathVariable Long id) {
        Optional<Review> rating = ratingService.findById(id);

        return ResponseEntity.ok(rating);
    }
}
