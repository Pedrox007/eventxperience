package com.project.eventxperience.sportevent.repository;

import com.project.eventxperience.sportevent.model.Sport;
import com.project.eventxperience.framework.repository.EventRepository;
import com.project.eventxperience.sportevent.model.SportEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SportEventRepository extends EventRepository<SportEvent> {
    List<SportEvent> findBySport(Sport sport);
}
