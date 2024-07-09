package com.project.eventxperience.musicevent.service;

import com.project.eventxperience.framework.service.base.UserPointStrategy;
import com.project.eventxperience.musicevent.model.Band;
import com.project.eventxperience.musicevent.repository.MusicEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MusicEventPointStrategy implements UserPointStrategy {
    private MusicEventRepository musicEventRepository;

    @Autowired
    public MusicEventPointStrategy(MusicEventRepository musicEventRepository) {
        this.musicEventRepository = musicEventRepository;
    }

    @Override
    public Long calculatePoints(Long userId, Long eventId) {
        Long points = 10L;

        List<Band> bands = musicEventRepository.findById(eventId).get().getBands();
        List<Long> bandsIds = bands.stream().map(Band::getId).toList();
        // Calculate points based on user's participation in the event
        // For example, 1 point for each event from the same band attended
        points += musicEventRepository.countByTickets_UserIdAndBands_Id_InAndTickets_ConfirmedIsTrue(userId, bandsIds);

        return points;
    }
}
