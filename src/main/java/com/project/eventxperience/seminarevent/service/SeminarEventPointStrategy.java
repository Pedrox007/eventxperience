package com.project.eventxperience.seminarevent.service;

import com.project.eventxperience.framework.service.base.UserPointStrategy;
import com.project.eventxperience.seminarevent.model.Speaker;
import com.project.eventxperience.seminarevent.repository.SeminarEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class SeminarEventPointStrategy implements UserPointStrategy {
    private SeminarEventRepository seminarEventRepository;

    @Autowired
    public SeminarEventPointStrategy(SeminarEventRepository seminarEventRepository) {
        this.seminarEventRepository = seminarEventRepository;
    }

    @Override
    public Long calculatePoints(Long userId, Long eventId) {
        Long points = 15L;

        List<Speaker> speakers = seminarEventRepository.findById(eventId).get().getSpeakers();
        List<Long> speakersIds = speakers.stream().map(Speaker::getId).toList();
        // Calculate points based on user's participation in the event
        // For example, 1 point for each event from the same speaker attended

        points += seminarEventRepository.countByTickets_UserIdAndSpeakers_Id_InAndTickets_ConfirmedIsTrue(userId, speakersIds);

        return points;
    }
}
