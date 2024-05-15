package com.project.eventxperience.model.dto;

import lombok.Data;

@Data
public class RatingDTO {
    private Long id;
    private String rating;
    private Long userId;
    private Long attractionId;
}
