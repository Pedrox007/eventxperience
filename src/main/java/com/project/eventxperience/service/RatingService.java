package com.project.eventxperience.service;

import com.project.eventxperience.model.Attraction;
import com.project.eventxperience.model.Rating;
import com.project.eventxperience.model.Reward;
import com.project.eventxperience.model.dto.RatingDTO;
import com.project.eventxperience.model.enums.RatingValues;
import com.project.eventxperience.repository.AttractionRepository;
import com.project.eventxperience.repository.RatingRepository;
import com.project.eventxperience.repository.RewardRepository;
import com.project.eventxperience.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private AttractionRepository attractionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    public Rating saveRating(RatingDTO ratingDTO) {
        try {
            Optional<Attraction> attraction = attractionRepository.findById(ratingDTO.getAttractionId());
            if (attraction.isEmpty()) {
                throw new IllegalArgumentException("Atração não encontrada");
            }

            Rating rating = new Rating();
            rating.setId(ratingDTO.getId());
            rating.setRating(RatingValues.valueOf(ratingDTO.getRating()));
            rating.setUser(userRepository.findById(ratingDTO.getUserId()).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado")));
            rating.setAttraction(attraction.get());
            userService.addPointsToUser(Long.valueOf(String.valueOf(userRepository.findById(ratingDTO.getUserId()))), 5);
            return ratingRepository.save(rating);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao salvar ou atualizar avaliação", e);
        }
    }

    public Rating deleteById(Long id) {
        try {
            Rating rating = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Avaliação não encontrada"));
            ratingRepository.deleteById(id);

            return rating;
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao excluir avaliação por ID", e);
        }
    }

    public List<Rating> findAllByUserId(Long userId) {
        try {
            return ratingRepository.findByUserId(userId);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao buscar todas as avaliações", e);
        }
    }

    public List<Rating> findAllByAttractionId(Long attractionId) {
        try {
            return ratingRepository.findByAttractionId(attractionId);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao buscar todas as avaliações", e);
        }
    }

    public List<Rating> findAllByEventId(Long eventId) {
        try {
            return ratingRepository.findByEventId(eventId);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao buscar todas as avaliações", e);
        }
    }

    public Optional<Rating> findById(Long id) {
        try {
            return ratingRepository.findById(id);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao buscar avaliação por ID", e);
        }
    }
}
