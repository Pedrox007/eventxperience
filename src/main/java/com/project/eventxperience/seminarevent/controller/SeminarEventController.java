package com.project.eventxperience.seminarevent.controller;

import com.project.eventxperience.framework.controller.base.BaseEventController;
import com.project.eventxperience.seminarevent.model.dto.request.SeminarEventRequestDTO;
import com.project.eventxperience.seminarevent.model.dto.response.SeminarEventResponseDTO;
import com.project.eventxperience.seminarevent.service.SeminarEventService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seminarEvents")
public class SeminarEventController extends BaseEventController<SeminarEventRequestDTO, SeminarEventResponseDTO> {
    protected SeminarEventController(SeminarEventService seminarEventService) {
        super(seminarEventService);
    }
}
