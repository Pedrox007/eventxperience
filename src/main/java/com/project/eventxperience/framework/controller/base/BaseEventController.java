package com.project.eventxperience.framework.controller.base;

import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.service.base.EventServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseEventController<RequestDTO, ResponseDTO> {
    EventServiceInterface<RequestDTO, ResponseDTO> eventService;

    protected BaseEventController(EventServiceInterface<RequestDTO, ResponseDTO> eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<ResponseDTO> retrieveEvent(@PathVariable Long id) {
        ResponseDTO eventObject = eventService.retrieveEventById(id);
        if (eventObject == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(eventObject);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ResponseDTO>> listEvents() {
        List<ResponseDTO> eventObjects = eventService.listEvent();

        if (eventObjects.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(eventObjects);
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> saveEvent(Authentication authentication, @RequestBody RequestDTO requestDTO) {
        User currentUser = (User) authentication.getPrincipal();
        ResponseDTO eventObject = eventService.saveEvent(currentUser, requestDTO);

        return ResponseEntity.ok(eventObject);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateEvent(Authentication authentication, @PathVariable Long id, @RequestBody RequestDTO requestDTO) {
        User currentUser = (User) authentication.getPrincipal();
        ResponseDTO eventObject = eventService.updateEvent(currentUser, requestDTO, id);

        return ResponseEntity.ok(eventObject);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteEvent(@PathVariable Long id) {
        ResponseDTO eventObject = eventService.deleteEvent(id);

        return ResponseEntity.ok(eventObject);
    }
}
