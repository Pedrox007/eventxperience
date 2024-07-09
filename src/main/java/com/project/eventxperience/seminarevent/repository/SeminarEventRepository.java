package com.project.eventxperience.seminarevent.repository;

import com.project.eventxperience.framework.repository.EventRepository;
import com.project.eventxperience.musicevent.model.MusicEvent;
import com.project.eventxperience.musicevent.model.enums.MusicGenreValue;
import com.project.eventxperience.seminarevent.model.SeminarEvent;
import com.project.eventxperience.seminarevent.model.Speaker;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface SeminarEventRepository extends EventRepository<SeminarEvent> {
    List<SeminarEvent> findBySpeakers_In(Set<Long> speakers_id);

    List<SeminarEvent> findByTheme(String theme);

    Long countByTickets_UserIdAndSpeakers_Id_InAndTickets_ConfirmedIsTrue(Long userId, List<Long> speakersIds);
}
