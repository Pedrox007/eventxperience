package com.project.eventxperience.musicevent.service;

import com.project.eventxperience.framework.model.Event;
import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.recommendation.RecommendationStrategy;
import com.project.eventxperience.framework.repository.TicketRepository;
import com.project.eventxperience.musicevent.model.MusicEvent;
import com.project.eventxperience.musicevent.model.Band;
import com.project.eventxperience.musicevent.model.dto.response.MusicEventResponseDTO;
import com.project.eventxperience.musicevent.model.enums.MusicGenreValue;
import com.project.eventxperience.musicevent.repository.MusicEventRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MusicEventRecommendationStrategy implements RecommendationStrategy<MusicEventResponseDTO> {

    private final MusicEventRepository musicEventRepository;
    private final TicketRepository ticketRepository;

    public MusicEventRecommendationStrategy(MusicEventRepository musicEventRepository, TicketRepository ticketRepository) {
        this.musicEventRepository = musicEventRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<MusicEventResponseDTO> recommend(User user) {
        // Obtêm os eventos confirmados do usuário
        List<Event> attendedEvents = ticketRepository.findConfirmedEventsByUser(user);

        // Encontra gêneros musicais dos eventos confirmados
        Set<MusicGenreValue> musicGenres = attendedEvents.stream()
        .filter(event -> event instanceof MusicEvent)
        .flatMap(event -> ((MusicEvent) event).getBands().stream()
                .map(Band::getMusicGenre))
        .collect(Collectors.toSet());

        // Encontra eventos semelhantes baseados nesses gêneros musicais
        List<MusicEvent> recommendedEvents = musicEventRepository.findByBands_MusicGenreIn(musicGenres).stream()
                .filter(event -> !attendedEvents.contains(event))
                .distinct()
                .collect(Collectors.toList());

        // Converte para MusicEventResponseDTOs
        List<MusicEventResponseDTO> recommendedEventDTOs = recommendedEvents.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return recommendedEventDTOs;
    }

    private MusicEventResponseDTO convertToDTO(MusicEvent musicEvent) {
        MusicEventResponseDTO dto = new MusicEventResponseDTO();
        dto.parseToDTO(musicEvent);
        return dto;
    }
}
