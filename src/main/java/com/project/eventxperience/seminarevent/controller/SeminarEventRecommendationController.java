package com.project.eventxperience.seminarevent.controller;

import com.project.eventxperience.framework.controller.RecommendationController;
import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.service.RecommendationService;
import com.project.eventxperience.seminarevent.model.dto.response.SeminarEventResponseDTO;
import com.project.eventxperience.seminarevent.service.SeminarEventRecommendationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/recommendations/seminar_events")
public class SeminarEventRecommendationController extends RecommendationController {

    private final SeminarEventRecommendationStrategy seminarEventRecommendationStrategy;

    @Autowired
    public SeminarEventRecommendationController(RecommendationService recommendationService, SeminarEventRecommendationStrategy seminarEventRecommendationStrategy) {
        super(recommendationService);
        this.seminarEventRecommendationStrategy = seminarEventRecommendationStrategy;
    }

    @Override
    @GetMapping
    public List<SeminarEventResponseDTO> getEventRecommendations(Authentication authentication) {
        recommendationService.changeRecommendationStrategy(seminarEventRecommendationStrategy);
        User currentUser = (User) authentication.getPrincipal();
        return recommendationService.generateRecommendations(currentUser);
    }
}
