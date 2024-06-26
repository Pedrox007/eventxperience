package com.project.eventxperience.framework.repository;

import com.project.eventxperience.framework.sportevent.model.SportEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportEventRepository extends CrudRepository<SportEvent, Long> {
    @Override
    Iterable<SportEvent> findAll();
}