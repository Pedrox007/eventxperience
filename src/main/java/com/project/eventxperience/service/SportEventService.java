package com.project.eventxperience.service;

import com.project.eventxperience.model.SportEvent;
import com.project.eventxperience.repository.SportEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SportEventService {

    private final SportEventRepository sportEventRepository;

    @Autowired
    public SportEventService(SportEventRepository sportEventRepository) {
        this.sportEventRepository = sportEventRepository;
    }
}
