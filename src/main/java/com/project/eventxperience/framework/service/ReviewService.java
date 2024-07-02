package com.project.eventxperience.framework.service;

import com.project.eventxperience.framework.model.Attraction;
import com.project.eventxperience.framework.model.Review;
import com.project.eventxperience.framework.model.dto.ReviewDTO;
import com.project.eventxperience.framework.model.enums.ReviewValues;
import com.project.eventxperience.framework.repository.AttractionRepository;
import com.project.eventxperience.framework.repository.ReviewRepository;
import com.project.eventxperience.framework.repository.UserRepository;

import com.project.eventxperience.framework.service.base.AttractionReviewSaveStrategy;
import com.project.eventxperience.framework.service.base.UserPointStrategyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private AttractionRepository attractionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    private AttractionReviewSaveStrategy attractionStrategy;
    private UserPointStrategyInterface userPointStrategy;

    public Review saveReview(ReviewDTO reviewDTO) {
        return attractionStrategy.execute(reviewDTO);
    }

    public Review deleteById(Long id) {
        try {
            Review review = reviewRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Avaliação não encontrada"));
            reviewRepository.deleteById(id);

            return review;
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao excluir avaliação por ID", e);
        }
    }

    public List<Review> findAllByUserId(Long userId) {
        try {
            return reviewRepository.findByUserId(userId);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao buscar todas as avaliações", e);
        }
    }

    public List<Review> findAllByAttractionId(Long attractionId) {
        try {
            return reviewRepository.findByAttractionId(attractionId);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao buscar todas as avaliações", e);
        }
    }

//    public List<Review> findAllByEventId(Long eventId) {
//        try {
//            return ratingRepository.findByEventId(eventId);
//        } catch (DataAccessException e) {
//            throw new IllegalStateException("Erro ao buscar todas as avaliações", e);
//        }
//    }

    public Optional<Review> findById(Long id) {
        try {
            return reviewRepository.findById(id);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao buscar avaliação por ID", e);
        }
    }

    public void changeStrategy(AttractionReviewSaveStrategy newAttractionStrategy) {
        attractionStrategy = newAttractionStrategy;
    }
}
