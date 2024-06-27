package com.project.eventxperience.framework.controller.base;

import com.project.eventxperience.framework.service.base.EventServiceInterface;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseDTO> saveEvent(@RequestBody RequestDTO requestDTO) {
        ResponseDTO eventObject = eventService.saveEvent(requestDTO);

        return ResponseEntity.ok(eventObject);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateEvent(@PathVariable Long id, @RequestBody RequestDTO requestDTO) {
        ResponseDTO eventObject = eventService.updateEvent(requestDTO, id);

        return ResponseEntity.ok(eventObject);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteEvent(@PathVariable Long id) {
        ResponseDTO eventObject = eventService.deleteEvent(id);

        return ResponseEntity.ok(eventObject);
    }
}
