package com.project.eventxperience.framework.service;

import com.project.eventxperience.framework.model.Event;
import com.project.eventxperience.framework.model.Review;
import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.model.dto.ReviewDTO;
import com.project.eventxperience.framework.repository.AttractionRepository;
import com.project.eventxperience.framework.repository.EventRepository;
import com.project.eventxperience.framework.repository.ReviewRepository;
import com.project.eventxperience.framework.repository.UserRepository;
import com.project.eventxperience.framework.service.base.UserPointStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    UserPointStrategy userPointStrategy;
    EventRepository<Event> eventRepository;
    AttractionRepository attractionRepository;

    @Autowired
    public ReviewService(EventRepository<Event> eventRepository, AttractionRepository attractionRepository, ReviewRepository reviewRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.attractionRepository = attractionRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    public void changeStrategy(UserPointStrategy userPointStrategy) {
        this.userPointStrategy = userPointStrategy;
    }

    public ReviewDTO saveEventReview(User authenticatedUser, ReviewDTO reviewDTO) {
        Long points = 0L;
        if (reviewDTO.getEventId() != null) {
            if (eventRepository.findById(reviewDTO.getEventId()).isEmpty()) {
                throw new IllegalArgumentException("Event not found");
            } else if (eventRepository.findByTickets_UserIdAndIdAndTickets_ConfirmedIsTrue(authenticatedUser.getId(), reviewDTO.getEventId()).isEmpty()) {
                throw new IllegalArgumentException("You must have attended the event to review it");
            } else if (reviewRepository.findByUserIdAndEventId(authenticatedUser.getId(), reviewDTO.getEventId()).isPresent()) {
                throw new IllegalArgumentException("You can only review an event once");
            } else if (reviewDTO.getReviewValue() == null) {
                throw new IllegalArgumentException("Review value is required");
            }
            points = userPointStrategy.calculatePoints(authenticatedUser.getId(), reviewDTO.getEventId());
        } else {
            throw new IllegalArgumentException("Event ID is required");
        }

        Review review = reviewDTO.parseToEntity();
        review.setUser(authenticatedUser);
        review.setEvent(eventRepository.findById(reviewDTO.getEventId()).get());
        review = reviewRepository.save(review);
        authenticatedUser.setPoints(authenticatedUser.getPoints() + points);
        userRepository.save(authenticatedUser);

        reviewDTO.parseToDTO(review);

        return reviewDTO;
    }

    public ReviewDTO saveAttractionReview(User authenticatedUser, ReviewDTO reviewDTO) {
        if (reviewDTO.getAttractionId() != null) {
            if (attractionRepository.findById(reviewDTO.getAttractionId()).isEmpty()) {
                throw new IllegalArgumentException("Attraction not found");
            } else if (reviewRepository.findByUserIdAndAttractionId(authenticatedUser.getId(), reviewDTO.getAttractionId()).isPresent()) {
            throw new IllegalArgumentException("You can only review an attraction once");
            } else if (reviewDTO.getReviewValue() == null) {
                throw new IllegalArgumentException("Review value is required");
            }
        } else {
            throw new IllegalArgumentException("Attraction ID is required");
        }

        Review review = reviewDTO.parseToEntity();
        review.setUser(authenticatedUser);
        review.setAttraction(attractionRepository.findById(reviewDTO.getAttractionId()).get());
        review = reviewRepository.save(review);
        authenticatedUser.setPoints(authenticatedUser.getPoints() + 10L);
        userRepository.save(authenticatedUser);

        reviewDTO.parseToDTO(review);

        return reviewDTO;
    }

    public ReviewDTO deleteReview(User authenticatedUser, Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("Review not found"));
        if (!review.getUser().getId().equals(authenticatedUser.getId())) {
            throw new IllegalArgumentException("You can only delete your own reviews");
        }

        reviewRepository.delete(review);

        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.parseToDTO(review);

        return reviewDTO;
    }

    public List<ReviewDTO> listReviewsByEvent(Long eventId) {
        List<Review> reviews = reviewRepository.findByEventId(eventId);
        List<ReviewDTO> reviewDTOS = new ArrayList<>();

        reviews.forEach(review -> {
            ReviewDTO reviewDTO = new ReviewDTO();
            reviewDTO.parseToDTO(review);
            reviewDTOS.add(reviewDTO);
        });

        return reviewDTOS;
    }

    public List<ReviewDTO> listReviewsByAttraction(Long attractionId) {
        List<Review> reviews = reviewRepository.findByAttractionId(attractionId);
        List<ReviewDTO> reviewDTOS = new ArrayList<>();

        reviews.forEach(review -> {
            ReviewDTO reviewDTO = new ReviewDTO();
            reviewDTO.parseToDTO(review);
            reviewDTOS.add(reviewDTO);
        });

        return reviewDTOS;
    }

    public List<ReviewDTO> listReviewsByUser(Long userId) {
        List<Review> reviews = reviewRepository.findByUserId(userId);
        List<ReviewDTO> reviewDTOS = new ArrayList<>();

        reviews.forEach(review -> {
            ReviewDTO reviewDTO = new ReviewDTO();
            reviewDTO.parseToDTO(review);
            reviewDTOS.add(reviewDTO);
        });

        return reviewDTOS;
    }
}
