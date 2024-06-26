package com.project.eventxperience.framework.repository;

import com.project.eventxperience.framework.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<Review, Long> {
    List<com.project.eventxperience.model.Review> findByUserId(Long user_id);

    List<com.project.eventxperience.model.Review> findByAttractionId(Long attraction_id);

    @Query("SELECT r FROM Rating r JOIN Attraction a ON r.attraction.id = a.id WHERE a.sportEvent.id = ?1")
    List<com.project.eventxperience.model.Review> findByEventId(Long eventId);
}
