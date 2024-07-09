package com.project.eventxperience.seminarevent.service;

import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.service.base.EventServiceInterface;
import com.project.eventxperience.seminarevent.model.SeminarEvent;
import com.project.eventxperience.seminarevent.model.Speaker;
import com.project.eventxperience.seminarevent.model.dto.request.SeminarEventRequestDTO;
import com.project.eventxperience.seminarevent.model.dto.response.SeminarEventResponseDTO;
import com.project.eventxperience.seminarevent.repository.SeminarEventRepository;
import com.project.eventxperience.seminarevent.repository.SpeakerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeminarEventService implements EventServiceInterface<SeminarEventRequestDTO, SeminarEventResponseDTO> {
    @Autowired
    private SeminarEventRepository seminarEventRepository;
    @Autowired
    private SpeakerRepository speakerRepository;

    @Override
    public SeminarEventResponseDTO saveEvent(User authenticatedUser, SeminarEventRequestDTO seminarEventRequestDTO) {
        SeminarEvent seminarEvent = seminarEventRequestDTO.parseToEntity();

        List<Speaker> speakers = validateSpeakers(seminarEventRequestDTO);
        seminarEvent.setSpeakers(speakers);
        seminarEvent.setCreator(authenticatedUser);

        seminarEventRepository.save(seminarEvent);

        SeminarEventResponseDTO seminarEventResponseDTO = new SeminarEventResponseDTO();
        seminarEventResponseDTO.parseToDTO(seminarEvent);

        return seminarEventResponseDTO;
    }

    @Override
    public SeminarEventResponseDTO retrieveEventById(Long id) {
        SeminarEvent seminarEvent = seminarEventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Seminare event does not exist!"));

        SeminarEventResponseDTO seminarEventResponseDTO = new SeminarEventResponseDTO();
        seminarEventResponseDTO.parseToDTO(seminarEvent);

        return seminarEventResponseDTO;
    }

    @Override
    public SeminarEventResponseDTO deleteEvent(Long id) {
        SeminarEvent seminarEvent = seminarEventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Seminar event does not exist!"));

        seminarEventRepository.deleteById(id);

        SeminarEventResponseDTO seminarEventResponseDTO = new SeminarEventResponseDTO();
        seminarEventResponseDTO.parseToDTO(seminarEvent);

        return seminarEventResponseDTO;
    }

    @Override
    public SeminarEventResponseDTO updateEvent(User authenticatedUser, SeminarEventRequestDTO seminarEventRequestDTO, Long id) {
        if (seminarEventRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Seminar Event not found!");
        }

        SeminarEvent seminarEvent = seminarEventRequestDTO.parseToEntity();

        seminarEvent.setSpeakers(validateSpeakers(seminarEventRequestDTO));
        seminarEvent.setId(id);
        seminarEvent.setCreator(authenticatedUser);

        seminarEventRepository.save(seminarEvent);

        SeminarEventResponseDTO seminarEventResponseDTO = new SeminarEventResponseDTO();
        seminarEventResponseDTO.parseToDTO(seminarEvent);

        return seminarEventResponseDTO;
    }

    @Override
    public List<SeminarEventResponseDTO> listEvent() {
        Iterable<SeminarEvent> events = seminarEventRepository.findAll();
        List<SeminarEvent> seminarEvents = new ArrayList<>();
        events.forEach(seminarEvents::add);

        List<SeminarEventResponseDTO> seminarEventResponseDTOS = new ArrayList<>();
        seminarEvents.forEach(seminarEvent -> {
            SeminarEventResponseDTO seminarEventResponseDTO = new SeminarEventResponseDTO();
            seminarEventResponseDTO.parseToDTO(seminarEvent);
            seminarEventResponseDTOS.add(seminarEventResponseDTO);
        });

        return  seminarEventResponseDTOS;
    }

    private List<Speaker> validateSpeakers(SeminarEventRequestDTO seminarEventRequestDTO) {
        List<Speaker> speakers = new ArrayList<>();

        seminarEventRequestDTO.getSpeakers().forEach(speakerDTO -> {
            Speaker speaker = new Speaker();

            if (speakerDTO.getId() != null) {
                speaker = speakerRepository.findById(speakerDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Speaker with id " + speakerDTO.getId() + " does not exist!"));
            } else {
                speaker.setName(speakerDTO.getName());
                speaker.setExpertise(speakerDTO.getExpertise());
                speaker = speakerRepository.save(speaker);
            }

            speakers.add(speaker);
        });

        return speakers;
    }
}
