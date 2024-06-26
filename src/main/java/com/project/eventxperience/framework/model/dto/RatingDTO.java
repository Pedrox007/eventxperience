package com.project.eventxperience.framework.model.dto;

import lombok.Data;

@Data
public class RatingDTO {
    private Long id;
    private String rating;
    private Long userId;
    private Long attractionId;
}
