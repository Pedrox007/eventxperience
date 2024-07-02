package com.project.eventxperience.sportevent.service;

import com.project.eventxperience.framework.model.Event;
import com.project.eventxperience.framework.repository.EventRepository;
import com.project.eventxperience.framework.service.base.EventServiceInterface;
import com.project.eventxperience.sportevent.model.Sport;
import com.project.eventxperience.sportevent.model.SportEvent;
import com.project.eventxperience.sportevent.model.dto.request.SportEventRequestDTO;
import com.project.eventxperience.sportevent.model.dto.response.SportEventResponseDTO;
import com.project.eventxperience.sportevent.repository.SportEventRepository;
import com.project.eventxperience.sportevent.repository.SportRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SportEventService implements EventServiceInterface<SportEventRequestDTO, SportEventResponseDTO> {
    @Autowired
    private SportEventRepository sportEventRepository;
    @Autowired
    private SportRepository sportRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public SportEventResponseDTO saveEvent(SportEventRequestDTO sportEventRequestDTO) {
        Optional<Sport> sport = Optional.ofNullable(sportRepository.findById(sportEventRequestDTO.getSportId()).orElseThrow(() -> new EntityNotFoundException("Sport does not exist!")));

        SportEvent sportEvent = sportEventRequestDTO.parseToEntity();
        sport.ifPresent(sportEvent::setSport);

        SportEventResponseDTO sportEventResponseDTO = new SportEventResponseDTO();
        sportEventResponseDTO.parseToDTO(sportEventRepository.save(sportEvent));

        return sportEventResponseDTO;
    }

    @Override
    public SportEventResponseDTO retrieveEventById(Long id) {
        Optional<Event> sportEvent = sportEventRepository.findById(id);
        if (sportEvent.isEmpty()) {
            return null;
        }

        SportEventResponseDTO sportEventResponseDTO = new SportEventResponseDTO();
        sportEventResponseDTO.parseToDTO((SportEvent) sportEvent.get());

        return sportEventResponseDTO;
    }

    @Override
    public SportEventResponseDTO deleteEvent(Long id) {
        Optional<Event> sportEvent = sportEventRepository.findById(id);
        if (sportEvent.isEmpty()) {
            throw new EntityNotFoundException("Sport Event not found!");
        }

        sportEventRepository.deleteById(id);

        SportEventResponseDTO sportEventResponseDTO = new SportEventResponseDTO();
        sportEventResponseDTO.parseToDTO((SportEvent) sportEvent.get());

        return sportEventResponseDTO;
    }

    @Override
    public SportEventResponseDTO updateEvent(SportEventRequestDTO sportEventRequestDTO, Long id) {
        if (sportEventRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Sport Event not found!");
        }
        Optional<Sport> sport = sportRepository.findById(sportEventRequestDTO.getSportId());
        if (sport.isEmpty()) {
            throw new EntityNotFoundException("Sport not found!");
        }

        SportEvent sportEvent = sportEventRequestDTO.parseToEntity();
        sportEvent.setId(id);
        sportEvent.setSport(sport.get());

        sportEventRepository.save(sportEvent);

        SportEventResponseDTO sportEventResponseDTO = new SportEventResponseDTO();
        sportEventResponseDTO.parseToDTO(sportEvent);

        return sportEventResponseDTO;
    }

    @Override
    public List<SportEventResponseDTO> listEvent() {
        Iterable<Event> events = eventRepository.findAll();
        List<SportEvent> sportEvents = new ArrayList<>();

        for (Event event : events) {
            if (event instanceof SportEvent) {
                sportEvents.add((SportEvent) event);
            }
        }

        return sportEvents.stream()
                .map(sportEvent -> {
                    SportEventResponseDTO sportEventResponseDTO = new SportEventResponseDTO();
                    sportEventResponseDTO.parseToDTO(sportEvent);
                    return sportEventResponseDTO;
                })
                .collect(Collectors.toList());
    }
}
