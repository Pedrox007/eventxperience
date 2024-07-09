package com.project.eventxperience.sportevent.service;

import com.project.eventxperience.framework.service.base.UserPointStrategy;
import com.project.eventxperience.sportevent.repository.SportEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SportEventPointStrategy implements UserPointStrategy {
    SportEventRepository sportEventRepository;

    @Autowired
    public SportEventPointStrategy(SportEventRepository sportEventRepository) {
        this.sportEventRepository = sportEventRepository;
    }

    @Override
    public Long calculatePoints(Long userId, Long eventId) {
        Long points = 15L;

        Long sportId = sportEventRepository.findById(eventId).get().getSport().getId();
        // Calculate points based on user's participation in the event
        // For example, 1 point for each event from the same sport attended
        points += sportEventRepository.countByTickets_UserIdAndSportIdAndTickets_ConfirmedIsTrue(userId, sportId);

        return points;
    }
}
