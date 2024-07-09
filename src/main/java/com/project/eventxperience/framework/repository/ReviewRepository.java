package com.project.eventxperience.framework.repository;

import com.project.eventxperience.framework.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUserId(Long user_id);

    List<Review> findByAttractionId(Long attraction_id);

    List<Review> findByEventId(Long eventId);

    Optional<Review> findByUserIdAndAttractionId(Long id, Long attractionId);

    Optional<Review> findByUserIdAndEventId(Long id, Long eventId);
}
