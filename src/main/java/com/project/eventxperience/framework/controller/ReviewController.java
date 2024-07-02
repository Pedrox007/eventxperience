package com.project.eventxperience.framework.controller;

import com.project.eventxperience.framework.model.Review;
import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.model.dto.ReviewDTO;
import com.project.eventxperience.framework.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public abstract class ReviewController {
    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody ReviewDTO reviewDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        reviewDTO.setUserId(currentUser.getId());

        Review createdReview = reviewService.saveReview(reviewDTO);

        return ResponseEntity.ok(createdReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Review> deleteReview(@PathVariable Long id) {
        Review reviewDeleted = reviewService.deleteById(id);

        return ResponseEntity.ok(reviewDeleted);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Iterable<Review>> getAllReviewsByUserId(@PathVariable Long userId) {
        Iterable<Review> reviews = reviewService.findAllByUserId(userId);

        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/attraction/{attractionId}")
    public ResponseEntity<Iterable<Review>> getAllReviewsByAttractionId(@PathVariable Long attractionId) {
        Iterable<Review> reviews = reviewService.findAllByAttractionId(attractionId);

        return ResponseEntity.ok(reviews);
    }

//    @GetMapping("/event/{eventId}")
//    public ResponseEntity<Iterable<Review>> getAllRatingsByEventId(@PathVariable Long eventId) {
//        Iterable<Review> ratings = ratingService.findAllByEventId(eventId);
//
//        return ResponseEntity.ok(ratings);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Review>> getReviewById(@PathVariable Long id) {
        Optional<Review> review = reviewService.findById(id);

        return ResponseEntity.ok(review);
    }
}
