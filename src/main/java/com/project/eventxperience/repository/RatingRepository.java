package com.project.eventxperience.repository;

import com.project.eventxperience.model.Rating;
import com.project.eventxperience.model.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByUserId(Long user_id);

    List<Rating> findByAttractionId(Long attraction_id);

    @Query("SELECT r FROM Rating r JOIN Attraction a ON r.attraction.id = a.id WHERE a.sportEvent.id = ?1")
    List<Rating> findByEventId(Long eventId);
}
