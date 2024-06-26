package com.project.eventxperience.framework.repository;

import com.project.eventxperience.framework.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository<M extends Event> extends JpaRepository<M, Long> {
}
