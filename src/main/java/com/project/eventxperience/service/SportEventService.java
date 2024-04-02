package com.project.eventxperience.service;

import com.project.eventxperience.model.Sport;
import com.project.eventxperience.model.SportEvent;
import com.project.eventxperience.model.dto.SportEventDTO;
import com.project.eventxperience.repository.SportEventRepository;
import com.project.eventxperience.repository.SportRepository;
import com.project.eventxperience.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SportEventService {

    private final SportEventRepository sportEventRepository;
    private final SportRepository sportRepository;
    private final UserRepository userRepository;

    @Autowired
    public SportEventService(SportEventRepository sportEventRepository, SportRepository sportRepository, UserRepository userRepository) {
        this.sportEventRepository = sportEventRepository;
        this.sportRepository = sportRepository;
        this.userRepository = userRepository;
    }

    public SportEvent addSportEvent(SportEventDTO sportEventDTO) {
        SportEvent sportEvent = new SportEvent();
        sportEvent.setName(sportEventDTO.getName());
        sportEvent.setEventDate(sportEventDTO.getEventDate());
        sportEvent.setDescription(sportEventDTO.getDescription());
        sportEvent.setTicketQuantity(sportEventDTO.getTicketQuantity());
        sportEvent.setTicketPrice(sportEventDTO.getTicketPrice());
        sportEvent.setCreator(userRepository.findById(sportEventDTO.getCreator_id()).orElse(null));
        if (sportEventDTO.getSport_id() != null) {
            Sport sport = sportRepository.findById(sportEventDTO.getSport_id()).orElse(null);

            sportEvent.setSport(sport);
        }

        sportEventRepository.save(sportEvent);

        return sportEvent;
    }
}
