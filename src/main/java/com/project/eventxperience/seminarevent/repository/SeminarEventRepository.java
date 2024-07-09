package com.project.eventxperience.seminarevent.repository;

import com.project.eventxperience.framework.repository.EventRepository;
import com.project.eventxperience.seminarevent.model.SeminarEvent;
import org.springframework.stereotype.Repository;

@Repository
public interface SeminarEventRepository extends EventRepository<SeminarEvent> {
}
