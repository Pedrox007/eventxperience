package com.project.eventxperience.sportevent.controller;

import com.project.eventxperience.framework.controller.ReviewController;
import com.project.eventxperience.framework.service.ReviewService;
import com.project.eventxperience.sportevent.service.SportEventReviewStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rating")
public class SportEventReviewController extends ReviewController {
    private SportEventReviewStrategyService SportEventReviewStrategyService;

    @Autowired
    public SportEventReviewController(ReviewService reviewService) {
        super(reviewService);
        reviewService.createStrategy(new SportEventReviewStrategyService());
    }
}
