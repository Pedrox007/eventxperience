package com.project.eventxperience.repository;

import com.project.eventxperience.model.SportEvent;
import com.project.eventxperience.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportEventRepository extends CrudRepository<SportEvent, Long> {
    @Override
    Iterable<SportEvent> findAll();
}