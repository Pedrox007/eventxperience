package com.project.eventxperience.framework.service;

import com.project.eventxperience.framework.sportevent.model.Sport;
import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.sportevent.model.SportEvent;
import com.project.eventxperience.framework.model.Ticket;
import com.project.eventxperience.framework.model.dto.SportEventDTO;
import com.project.eventxperience.framework.repository.SportEventRepository;
import com.project.eventxperience.framework.repository.SportRepository;
import com.project.eventxperience.framework.repository.TicketRepository;
import com.project.eventxperience.framework.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SportEventService {

    private final SportEventRepository sportEventRepository;
    private final SportRepository sportRepository;
    private final UserRepository userRepository;
    private TicketRepository ticketRepository;

    @Autowired
    public SportEventService(SportEventRepository sportEventRepository, SportRepository sportRepository, UserRepository userRepository) {
        this.sportEventRepository = sportEventRepository;
        this.sportRepository = sportRepository;
        this.userRepository = userRepository;
    }

    public SportEvent addSportEvent(SportEventDTO sportEventDTO) {
        try {
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

            return sportEventRepository.save(sportEvent);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao adicionar evento de esporte", e);
        }
    }

    public Iterable<SportEvent> getSportEvents() {
        try {
            return sportEventRepository.findAll();
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao obter eventos de esporte", e);
        }
    }

    public SportEvent findById(Long id) {
        try {
            return sportEventRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado com o ID: " + id));
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao buscar evento por ID", e);
        }
    }

    public List<User> getAllParticipantsByEventId(Long eventId) {
        try {
            SportEvent event = sportEventRepository.findById(eventId).orElse(null);
            if (event != null) {
                return event.getTickets().stream()
                        .map(Ticket::getUser)
                        .collect(Collectors.toList());
            }
            return Collections.emptyList();
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao buscar participantes do evento por ID", e);
        }
    }
}
