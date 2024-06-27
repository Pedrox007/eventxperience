package com.project.eventxperience.framework.repository;

import com.project.eventxperience.framework.model.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository<M extends Event> extends CrudRepository<M, Long> {
}
