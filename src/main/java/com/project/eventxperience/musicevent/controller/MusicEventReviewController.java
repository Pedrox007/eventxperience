package com.project.eventxperience.musicevent.controller;

import com.project.eventxperience.framework.controller.ReviewController;
import com.project.eventxperience.framework.model.dto.ReviewDTO;
import com.project.eventxperience.framework.service.ReviewService;
import com.project.eventxperience.musicevent.service.MusicEventPointStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/musicEvent")
public class MusicEventReviewController extends ReviewController {
    private final MusicEventPointStrategy musicEventPointStrategy;

    @Autowired
    protected MusicEventReviewController(ReviewService reviewService, MusicEventPointStrategy musicEventPointStrategy) {
        super(reviewService);
        this.musicEventPointStrategy = musicEventPointStrategy;
    }

    @Override
    public ResponseEntity<ReviewDTO> createEventReview(Authentication authentication, ReviewDTO reviewDTO) {
        reviewService.changeStrategy(musicEventPointStrategy);

        return super.createEventReview(authentication, reviewDTO);
    }
}
