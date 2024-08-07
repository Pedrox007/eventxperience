package com.project.eventxperience.framework.recommendation;

import com.project.eventxperience.framework.model.Event;
import com.project.eventxperience.framework.model.User;

import java.util.List;

public interface RecommendationStrategy<T> {
    List<T> recommend(User user);
}

