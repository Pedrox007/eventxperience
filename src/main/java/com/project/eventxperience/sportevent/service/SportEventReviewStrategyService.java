package com.project.eventxperience.sportevent.service;

import com.project.eventxperience.framework.model.enums.ReviewValues;
import com.project.eventxperience.framework.repository.ReviewRepository;
import com.project.eventxperience.framework.service.UserService;
import com.project.eventxperience.framework.service.base.AttractionReviewStrategyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SportEventReviewStrategyService implements AttractionReviewStrategyInterface {
    @Autowired
    private UserService userService;

    private ReviewRepository reviewRepository;

    @Override
    public int execute() {
//        if (!authorization.startsWith("Bearer ")) {
//            throw new IllegalArgumentException("Bearer token is missing or invalid");
//        }
//        String token = authorization.substring(7);
//        Long id = userService.getUserByToken(token).getId();


        return 5;
    }
}
