package com.project.eventxperience.sportevent.service;

import com.project.eventxperience.framework.service.base.UserPointStrategyInterface;

public class SportEventPointService implements UserPointStrategyInterface {
    @Override
    public int execute() {
        return 5;
    }
}
