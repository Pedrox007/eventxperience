package com.project.eventxperience.sportevent.controller;

import com.project.eventxperience.framework.controller.RecommendationController;
import com.project.eventxperience.framework.model.Event;
import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.service.RecommendationService;
import com.project.eventxperience.sportevent.service.SportEventRecommendationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sport_event_recommendations")
public class SportEventRecommendationController extends RecommendationController {

    @Autowired
    public SportEventRecommendationController(RecommendationService recommendationService, SportEventRecommendationStrategy sportEventRecommendationStrategy) {
        super(recommendationService);
        this.recommendationService.changeRecommendationStrategy(sportEventRecommendationStrategy);
    }
}