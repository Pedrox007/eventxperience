package com.project.eventxperience.framework.repository;

import com.project.eventxperience.framework.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUserId(Long user_id);

    List<Review> findByAttractionId(Long attraction_id);
}
