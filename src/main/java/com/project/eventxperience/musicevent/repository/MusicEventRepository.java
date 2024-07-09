package com.project.eventxperience.musicevent.repository;

import com.project.eventxperience.framework.repository.EventRepository;
import com.project.eventxperience.musicevent.model.MusicEvent;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicEventRepository extends EventRepository<MusicEvent> {
}
