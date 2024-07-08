package com.project.eventxperience.sportevent.service;

import com.project.eventxperience.framework.model.Event;
import com.project.eventxperience.framework.model.User;
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

    @Override
    public SportEventResponseDTO saveEvent(User authenticatedUser, SportEventRequestDTO sportEventRequestDTO) {
        Optional<Sport> sport = Optional.ofNullable(sportRepository.findById(sportEventRequestDTO.getSportId()).orElseThrow(() -> new EntityNotFoundException("Sport does not exist!")));

        SportEvent sportEvent = sportEventRequestDTO.parseToEntity();
        sport.ifPresent(sportEvent::setSport);
        sportEvent.setCreator(authenticatedUser);

        SportEventResponseDTO sportEventResponseDTO = new SportEventResponseDTO();
        sportEventResponseDTO.parseToDTO(sportEventRepository.save(sportEvent));

        return sportEventResponseDTO;
    }

    @Override
    public SportEventResponseDTO retrieveEventById(Long id) {
        SportEvent sportEvent = sportEventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sport event does not exist!"));

        SportEventResponseDTO sportEventResponseDTO = new SportEventResponseDTO();
        sportEventResponseDTO.parseToDTO(sportEvent);

        return sportEventResponseDTO;
    }

    @Override
    public SportEventResponseDTO deleteEvent(Long id) {
        SportEvent sportEvent = sportEventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sport event does not exist!"));

        sportEventRepository.deleteById(id);

        SportEventResponseDTO sportEventResponseDTO = new SportEventResponseDTO();
        sportEventResponseDTO.parseToDTO(sportEvent);

        return sportEventResponseDTO;
    }

    @Override
    public SportEventResponseDTO updateEvent(User authenticatedUser, SportEventRequestDTO sportEventRequestDTO, Long id) {
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
        sportEvent.setCreator(authenticatedUser);

        sportEventRepository.save(sportEvent);

        SportEventResponseDTO sportEventResponseDTO = new SportEventResponseDTO();
        sportEventResponseDTO.parseToDTO(sportEvent);

        return sportEventResponseDTO;
    }

    @Override
    public List<SportEventResponseDTO> listEvent() {
        Iterable<SportEvent> events = sportEventRepository.findAll();
        List<SportEvent> sportEvents = new ArrayList<>();
        events.forEach(sportEvents::add);

        return sportEvents.stream()
                .map(sportEvent -> {
                    SportEventResponseDTO sportEventResponseDTO = new SportEventResponseDTO();
                    sportEventResponseDTO.parseToDTO(sportEvent);
                    return sportEventResponseDTO;
                })
                .collect(Collectors.toList());
    }
}
