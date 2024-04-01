package com.project.eventxperience.service;

import com.project.eventxperience.model.User;
import com.project.eventxperience.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Transactional
    User saveUser(User user) {
        userRepository.save(user);

        return user;
    }
}