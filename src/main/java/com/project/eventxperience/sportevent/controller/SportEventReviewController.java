package com.project.eventxperience.sportevent.controller;

import com.project.eventxperience.framework.controller.ReviewController;
import com.project.eventxperience.framework.model.dto.ReviewDTO;
import com.project.eventxperience.framework.service.ReviewService;
import com.project.eventxperience.sportevent.service.SportEventPointStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sportEvent")
public class SportEventReviewController extends ReviewController {
    private final SportEventPointStrategy sportEventPointStrategy;

    @Autowired
    public SportEventReviewController(ReviewService reviewService, SportEventPointStrategy sportEventPointStrategy) {
        super(reviewService);
        this.sportEventPointStrategy = sportEventPointStrategy;
    }

    @Override
    public ResponseEntity<ReviewDTO> createEventReview(Authentication authentication, ReviewDTO reviewDTO) {
        reviewService.changeStrategy(sportEventPointStrategy);

        return super.createEventReview(authentication, reviewDTO);
    }
}
