package com.project.eventxperience.sportevent.repository;

import com.project.eventxperience.framework.repository.EventRepository;
import com.project.eventxperience.sportevent.model.SportEvent;
import org.springframework.stereotype.Repository;

@Repository
public interface SportEventRepository extends EventRepository<SportEvent> {
}