package com.project.eventxperience.musicevent.controller;

import com.project.eventxperience.framework.controller.RecommendationController;
import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.service.RecommendationService;
import com.project.eventxperience.musicevent.model.dto.response.MusicEventResponseDTO;
import com.project.eventxperience.musicevent.service.MusicEventRecommendationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommendations/music_events")
public class MusicEventRecommendationController extends RecommendationController {

    private final MusicEventRecommendationStrategy musicEventRecommendationStrategy;

    @Autowired
    public MusicEventRecommendationController(RecommendationService recommendationService, MusicEventRecommendationStrategy musicEventRecommendationStrategy) {
        super(recommendationService);
        this.musicEventRecommendationStrategy = musicEventRecommendationStrategy;
    }

    @Override
    @GetMapping
    public List<MusicEventResponseDTO> getEventRecommendations(Authentication authentication) {
        recommendationService.changeRecommendationStrategy(musicEventRecommendationStrategy);
        User currentUser = (User) authentication.getPrincipal();
        return recommendationService.generateRecommendations(currentUser);
    }
}
