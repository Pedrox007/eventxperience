package com.project.eventxperience.seminarevent.controller;

import com.project.eventxperience.framework.controller.ReviewController;
import com.project.eventxperience.framework.model.dto.ReviewDTO;
import com.project.eventxperience.framework.service.ReviewService;
import com.project.eventxperience.seminarevent.service.SeminarEventPointStrategy;
import com.project.eventxperience.sportevent.service.SportEventPointStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seminarEvent")
public class SeminarEventReviewController extends ReviewController {
    private final SeminarEventPointStrategy seminarEventPointStrategy;

    @Autowired
    public SeminarEventReviewController(ReviewService reviewService, SeminarEventPointStrategy seminarEventPointStrategy) {
        super(reviewService);
        this.seminarEventPointStrategy = seminarEventPointStrategy;
    }

    @Override
    public ResponseEntity<ReviewDTO> createEventReview(Authentication authentication, ReviewDTO reviewDTO) {
        reviewService.changeStrategy(seminarEventPointStrategy);
        return super.createEventReview(authentication, reviewDTO);
    }
}
