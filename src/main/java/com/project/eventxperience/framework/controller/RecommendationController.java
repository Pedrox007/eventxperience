package com.project.eventxperience.framework.controller;
import com.project.eventxperience.framework.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;


import java.util.List;

public abstract class RecommendationController<T> {

    protected final RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    public abstract List<T> getEventRecommendations(Authentication authentication);
}

