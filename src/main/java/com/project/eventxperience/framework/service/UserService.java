package com.project.eventxperience.framework.service;

import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void addPointsToUser(Long userId, int pointsToAdd) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));

            int currentPoints = user.getPoints();
            int newPoints = currentPoints + pointsToAdd;
            user.setPoints(newPoints);

            userRepository.save(user);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao adicionar pontos ao usuário", e);
        }
    }

}