package com.project.eventxperience.framework.controller;

import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.model.dto.ReviewDTO;
import com.project.eventxperience.framework.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class ReviewController {
    protected ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/createEventReview")
    public ResponseEntity<ReviewDTO> createEventReview(Authentication authentication, @RequestBody ReviewDTO reviewDTO) {
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(reviewService.saveEventReview(currentUser, reviewDTO));
    }

    @PostMapping("/createAttractionReview")
    public ResponseEntity<ReviewDTO> createAttractionReview(Authentication authentication, @RequestBody ReviewDTO reviewDTO) {
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(reviewService.saveAttractionReview(currentUser, reviewDTO));
    }

    @GetMapping("/listEventReviews/{id}")
    public ResponseEntity<List<ReviewDTO>> listEventReviews(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.listReviewsByEvent(id));
    }

    @GetMapping("/listAttractionReviews/{id}")
    public ResponseEntity<List<ReviewDTO>> listAttractionReviews(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.listReviewsByAttraction(id));
    }

    @GetMapping("/listUserReviews/{id}")
    public ResponseEntity<List<ReviewDTO>> listUserReviews(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.listReviewsByUser(id));
    }

    @DeleteMapping("/deleteReview/{id}")
    public ResponseEntity<ReviewDTO> deleteReview(Authentication authentication, @PathVariable Long id) {
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(reviewService.deleteReview(currentUser, id));
    }
}
