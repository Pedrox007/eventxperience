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
    private RecommendationStrategy recommendationStrategy;

    @Autowired
    public RecommendationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void changeRecommendationStrategy(RecommendationStrategy strategy) {
        this.recommendationStrategy = strategy;
    }

    public List<Event> generateRecommendations(User user) {
        if (recommendationStrategy == null) {
            throw new IllegalStateException("Nenhuma estratégia de recomendação definida.");
        }
        return recommendationStrategy.recommend(user);
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com id: " + userId));
    }
}
