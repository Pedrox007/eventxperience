package com.project.eventxperience.sportevent.controller;

import com.project.eventxperience.framework.controller.base.BaseEventController;
import com.project.eventxperience.sportevent.model.dto.request.SportEventRequestDTO;
import com.project.eventxperience.sportevent.model.dto.response.SportEventResponseDTO;
import com.project.eventxperience.sportevent.service.SportEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sportEvents")
public class SporteventController extends BaseEventController<SportEventRequestDTO, SportEventResponseDTO> {
    @Autowired
    public SporteventController(SportEventService sportEventService) {
        super(sportEventService);
    }
}
