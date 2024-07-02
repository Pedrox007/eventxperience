package com.project.eventxperience.sportevent.controller;

import com.project.eventxperience.framework.controller.ReviewController;
import com.project.eventxperience.framework.service.ReviewService;
import com.project.eventxperience.sportevent.service.SportReviewSaveSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class SportEventReviewController extends ReviewController {
    private SportReviewSaveSaveService SportReviewSaveService;

    @Autowired
    public SportEventReviewController(ReviewService reviewService, SportReviewSaveSaveService sportReviewSaveService) {
        super(reviewService);
        reviewService.changeStrategy(sportReviewSaveService);
    }


}
