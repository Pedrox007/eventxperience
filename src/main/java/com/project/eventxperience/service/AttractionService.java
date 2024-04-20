package com.project.eventxperience.service;

import com.project.eventxperience.model.Attraction;
import com.project.eventxperience.model.Sport;
import com.project.eventxperience.model.SportEvent;
import com.project.eventxperience.model.base.Event;
import com.project.eventxperience.model.dto.AttractionDTO;
import com.project.eventxperience.repository.AttractionRepository;
import com.project.eventxperience.repository.SportEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttractionService {

    private final AttractionRepository attractionRepository;
    private final SportEventRepository sportEventRepository;

    @Autowired
    public AttractionService(AttractionRepository attractionRepository, SportEventRepository sportEventRepository) {
        this.attractionRepository = attractionRepository;
        this.sportEventRepository = sportEventRepository;
    }

    public List<Attraction> saveAttractions(Long eventId,List<AttractionDTO> attractionsDTO) {
        List<Attraction> attractions = new ArrayList<>();
        Optional<SportEvent> event = sportEventRepository.findById(eventId);
        if (event.isEmpty()) {
            throw new IllegalArgumentException("Event not found");
        }

        for (AttractionDTO attractionDTO : attractionsDTO) {
            Attraction attraction = new Attraction();
            attraction.setId(attractionDTO.getId());
            attraction.setName(attractionDTO.getName());
            attraction.setSportEvent(event.get());
            attractions.add(attraction);
        }


        return Streamable.of(attractionRepository.saveAll(attractions)).toList();
    }

    public List<Attraction> getAttractions(Long eventId) {
        return attractionRepository.findAllByEventId(eventId);
    }
}