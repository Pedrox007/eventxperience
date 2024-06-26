package com.project.eventxperience.framework.model.dto;

import lombok.Data;

@Data
public class AttractionDTO {
    private Long id;
    private String name;
    private Long eventId = null;
}
