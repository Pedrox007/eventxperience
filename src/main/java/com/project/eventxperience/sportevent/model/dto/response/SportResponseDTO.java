package com.project.eventxperience.sportevent.model.dto.response;

import lombok.Data;

@Data
public class SportResponseDTO {
    private Long id;
    private String name;
    private Integer participants;
    private String description;
}
