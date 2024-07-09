package com.project.eventxperience.framework.service.base;

import org.springframework.stereotype.Component;

@Component
public interface UserPointStrategy {
    Long calculatePoints(Long userId, Long eventId);
}
