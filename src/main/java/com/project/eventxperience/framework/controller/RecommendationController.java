package com.project.eventxperience.framework.controller;

import com.project.eventxperience.framework.model.Event;
import com.project.eventxperience.framework.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public abstract class RecommendationController {

    protected final RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping
    public abstract List<Event> getEventRecommendations(Authentication authentication);
}