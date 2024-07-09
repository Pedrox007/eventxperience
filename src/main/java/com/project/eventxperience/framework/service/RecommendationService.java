package com.project.eventxperience.framework.service;

import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.model.Event;
import com.project.eventxperience.framework.repository.UserRepository;
import com.project.eventxperience.framework.recommendation.RecommendationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

    private final UserRepository userRepository;
    private RecommendationStrategy<?> recommendationStrategy;

    @Autowired
    public RecommendationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void changeRecommendationStrategy(RecommendationStrategy<?> strategy) {
        this.recommendationStrategy = strategy;
        System.out.println("mudou para" + this.recommendationStrategy);
    }

    public <T> List<T> generateRecommendations(User user) {
        if (recommendationStrategy == null) {
            throw new IllegalStateException("Nenhuma estratégia de recomendação definida.");
        }
        System.out.println("utilizando" + this.recommendationStrategy);
        return (List<T>) recommendationStrategy.recommend(user);
    }
}

