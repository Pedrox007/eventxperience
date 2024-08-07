package com.project.eventxperience.musicevent.repository;

import com.project.eventxperience.framework.repository.EventRepository;
import com.project.eventxperience.musicevent.model.MusicEvent;
import com.project.eventxperience.musicevent.model.enums.MusicGenreValue;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface MusicEventRepository extends EventRepository<MusicEvent> {
    List<MusicEvent> findByBands_MusicGenreIn(Set<MusicGenreValue> genres);

    Long countByTickets_UserIdAndBands_Id_InAndTickets_ConfirmedIsTrue(Long userId, List<Long> bands_id);
}

