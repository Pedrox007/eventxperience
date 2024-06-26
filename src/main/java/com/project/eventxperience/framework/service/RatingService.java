package com.project.eventxperience.framework.service;

import com.project.eventxperience.framework.model.Attraction;
import com.project.eventxperience.framework.model.dto.RatingDTO;
import com.project.eventxperience.framework.repository.AttractionRepository;
import com.project.eventxperience.framework.repository.RatingRepository;
import com.project.eventxperience.framework.repository.UserRepository;
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

    public com.project.eventxperience.model.Review saveRating(RatingDTO ratingDTO) {
        try {
            Optional<Attraction> attraction = attractionRepository.findById(ratingDTO.getAttractionId());
            if (attraction.isEmpty()) {
                throw new IllegalArgumentException("Atração não encontrada");
            }

            com.project.eventxperience.model.Review rating = new com.project.eventxperience.model.Review();
            rating.setId(ratingDTO.getId());
            rating.setRating(com.project.eventxperience.model.enums.ReviewValues.valueOf(ratingDTO.getRating()));
            rating.setUser(userRepository.findById(ratingDTO.getUserId()).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado")));
            rating.setAttraction(attraction.get());
            userService.addPointsToUser(ratingDTO.getUserId(), 5);
            return ratingRepository.save(rating);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao salvar ou atualizar avaliação", e);
        }
    }

    public com.project.eventxperience.model.Review deleteById(Long id) {
        try {
            com.project.eventxperience.model.Review rating = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Avaliação não encontrada"));
            ratingRepository.deleteById(id);

            return rating;
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao excluir avaliação por ID", e);
        }
    }

    public List<com.project.eventxperience.model.Review> findAllByUserId(Long userId) {
        try {
            return ratingRepository.findByUserId(userId);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao buscar todas as avaliações", e);
        }
    }

    public List<com.project.eventxperience.model.Review> findAllByAttractionId(Long attractionId) {
        try {
            return ratingRepository.findByAttractionId(attractionId);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao buscar todas as avaliações", e);
        }
    }

    public List<com.project.eventxperience.model.Review> findAllByEventId(Long eventId) {
        try {
            return ratingRepository.findByEventId(eventId);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao buscar todas as avaliações", e);
        }
    }

    public Optional<com.project.eventxperience.model.Review> findById(Long id) {
        try {
            return ratingRepository.findById(id);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao buscar avaliação por ID", e);
        }
    }
}
