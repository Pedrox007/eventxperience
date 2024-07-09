package com.project.eventxperience.framework.controller;
import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.service.RecommendationService;
import com.project.eventxperience.musicevent.model.dto.response.MusicEventResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

public abstract class RecommendationController<T> {

    protected final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping
    public List<MusicEventResponseDTO> getEventRecommendations(Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        return recommendationService.generateRecommendations(currentUser);
    }
}

