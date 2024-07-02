package com.project.eventxperience.framework.service.base;

import com.project.eventxperience.framework.model.Review;
import com.project.eventxperience.framework.model.dto.ReviewDTO;

public interface AttractionReviewSaveStrategy {
    Review execute(ReviewDTO reviewDTO);
}
