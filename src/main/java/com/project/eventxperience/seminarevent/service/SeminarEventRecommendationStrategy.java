package com.project.eventxperience.seminarevent.service;

import com.project.eventxperience.framework.model.Event;
import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.recommendation.RecommendationStrategy;
import com.project.eventxperience.framework.repository.TicketRepository;
import com.project.eventxperience.musicevent.model.Band;
import com.project.eventxperience.musicevent.model.MusicEvent;
import com.project.eventxperience.musicevent.model.dto.response.MusicEventResponseDTO;
import com.project.eventxperience.musicevent.model.enums.MusicGenreValue;
import com.project.eventxperience.seminarevent.model.SeminarEvent;
import com.project.eventxperience.seminarevent.model.Speaker;
import com.project.eventxperience.seminarevent.model.dto.response.SeminarEventResponseDTO;
import com.project.eventxperience.seminarevent.repository.SeminarEventRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SeminarEventRecommendationStrategy implements RecommendationStrategy<SeminarEventResponseDTO> {

    private final SeminarEventRepository seminarEventRepository;
    private final TicketRepository ticketRepository;

    public SeminarEventRecommendationStrategy(SeminarEventRepository seminarEventRepository, TicketRepository ticketRepository) {
        this.seminarEventRepository = seminarEventRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<SeminarEventResponseDTO> recommend(User user) {
        // Obtêm os eventos confirmados do usuário
        List<Event> attendedEvents = ticketRepository.findConfirmedEventsByUser(user);

        // Filtra eventos de seminário
        List<SeminarEvent> attendedSeminars = attendedEvents.stream()
                .filter(event -> event instanceof SeminarEvent)
                .map(event -> (SeminarEvent) event)
                .collect(Collectors.toList());

        // Extrai IDs dos palestrantes
        Set<Long> speakerIds = attendedSeminars.stream()
                .flatMap(seminarEvent -> seminarEvent.getSpeakers().stream())
                .map(Speaker::getId)
                .collect(Collectors.toSet());

        // Busca eventos recomendados com base nos palestrantes dos eventos assistidos
        List<SeminarEvent> recommendedEvents = seminarEventRepository.findBySpeakers_In(speakerIds).stream()
                .filter(event -> !attendedEvents.contains(event)) // Excluir os eventos já assistidos
                .distinct()
                .collect(Collectors.toList());

        // Converte para SeminarEventResponseDTOs
        List<SeminarEventResponseDTO> recommendedEventDTOs = recommendedEvents.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return recommendedEventDTOs;
    }

    private SeminarEventResponseDTO convertToDTO(SeminarEvent seminarEvent) {
        SeminarEventResponseDTO dto = new SeminarEventResponseDTO();
        dto.parseToDTO(seminarEvent);
        return dto;
    }
}
