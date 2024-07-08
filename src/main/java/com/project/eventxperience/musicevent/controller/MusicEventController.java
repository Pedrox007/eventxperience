package com.project.eventxperience.musicevent.controller;

import com.project.eventxperience.framework.controller.base.BaseEventController;
import com.project.eventxperience.musicevent.model.dto.request.MusicEventRequestDTO;
import com.project.eventxperience.musicevent.model.dto.response.MusicEventResponseDTO;
import com.project.eventxperience.musicevent.service.MusicEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/musicEvents")
public class MusicEventController extends BaseEventController<MusicEventRequestDTO, MusicEventResponseDTO>{
    @Autowired
    protected MusicEventController(MusicEventService eventService) {
        super(eventService);
    }
}
