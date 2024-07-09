package com.project.eventxperience.framework.model.dto;

import com.project.eventxperience.framework.model.Review;
import com.project.eventxperience.framework.model.dto.base.BaseDTO;
import com.project.eventxperience.framework.model.enums.ReviewValues;
import lombok.Data;

@Data
public class ReviewDTO implements BaseDTO<Review> {
    private Long id;
    private Long userId;
    private Long eventId;
    private Long attractionId;
    private ReviewValues reviewValue;

    @Override
    public Review parseToEntity() {
        Review review = new Review();
        review.setId(id);
        review.setReview(reviewValue);

        return review;
    }

    @Override
    public void parseToDTO(Review review) {
        id = review.getId();
        reviewValue = review.getReview();
        if (review.getEvent() != null) {
            eventId = review.getEvent().getId();
        }
        if (review.getAttraction() != null) {
            attractionId = review.getAttraction().getId();
        }
        userId = review.getUser().getId();
    }
}
