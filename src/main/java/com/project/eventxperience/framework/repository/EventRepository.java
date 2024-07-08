package com.project.eventxperience.framework.repository;

import com.project.eventxperience.framework.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository<E extends Event> extends CrudRepository<E, Long> {
}

