package com.project.eventxperience.sportevent.service;

import com.project.eventxperience.framework.model.Attraction;
import com.project.eventxperience.framework.model.Review;
import com.project.eventxperience.framework.model.enums.ReviewValues;
import com.project.eventxperience.framework.repository.AttractionRepository;
import com.project.eventxperience.framework.repository.ReviewRepository;
import com.project.eventxperience.framework.repository.UserRepository;
import com.project.eventxperience.framework.service.UserService;
import com.project.eventxperience.framework.service.base.AttractionReviewSaveStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.project.eventxperience.framework.model.dto.ReviewDTO;

import java.util.Optional;


@Service
public class SportReviewSaveService implements AttractionReviewSaveStrategy {

    @Autowired
    private UserService userService;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private AttractionRepository attractionRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public Review execute(ReviewDTO reviewDTO) {
        try {
            Optional<Attraction> attraction = attractionRepository.findById(reviewDTO.getAttractionId());
            if (attraction.isEmpty()) {
                throw new IllegalArgumentException("Atração não encontrada");
            }

            Review review = new Review();
            review.setId(reviewDTO.getId());
            review.setRating(ReviewValues.valueOf(reviewDTO.getRating()));
            review.setUser(userRepository.findById(reviewDTO.getUserId()).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado")));
            review.setAttraction(attraction.get());
            userService.addPointsToUser(reviewDTO.getUserId(), 5);
            return reviewRepository.save(review);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao salvar ou atualizar avaliação", e);
        }
    }
}
