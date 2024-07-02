package com.project.eventxperience.sportevent.service;

import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.model.Event;
import com.project.eventxperience.framework.repository.TicketRepository;
import com.project.eventxperience.framework.recommendation.RecommendationStrategy;
import com.project.eventxperience.sportevent.model.Sport;
import com.project.eventxperience.sportevent.model.SportEvent;
import com.project.eventxperience.sportevent.repository.SportEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Component
public class SportEventRecommendationStrategy implements RecommendationStrategy {

    private final SportEventRepository sportEventRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public SportEventRecommendationStrategy(SportEventRepository sportEventRepository, TicketRepository ticketRepository) {
        this.sportEventRepository = sportEventRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Event> recommend(User user) {
        // Obtem os eventos confirmados do usuário
        List<Event> attendedEvents = ticketRepository.findEventsByUser(user);

        // Encontra esportes dos eventos confirmados
        Set<Sport> sports = attendedEvents.stream()
                .filter(event -> event instanceof SportEvent) // Filtra apenas SportEvent
                .map(event -> ((SportEvent) event).getSport())
                .collect(Collectors.toSet());

        // Encontra eventos semelhantes baseados nesses esportes
        List<Event> recommendedEvents = sports.stream()
                .flatMap(sport -> sportEventRepository.findBySport(sport).stream())
                .filter(event -> !attendedEvents.contains(event)) // Exclui os eventos já assistidos
                .distinct()
                .collect(Collectors.toList());

        // Retorna os eventos recomendados como uma lista de Event
        return recommendedEvents;
    }
}