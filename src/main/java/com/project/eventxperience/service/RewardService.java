package com.project.eventxperience.service;

import com.project.eventxperience.model.Reward;
import com.project.eventxperience.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RewardService {

    @Autowired
    private RewardRepository rewardRepository;

    public Reward saveOrUpdate(Reward reward) {
        try {
            return rewardRepository.save(reward);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao salvar ou atualizar recompensa", e);
        }
    }

    public void deleteById(Long id) {
        try {
            rewardRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao excluir recompensa por ID", e);
        }
    }

    public List<Reward> findAll() {
        try {
            return rewardRepository.findAll();
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao buscar todas as recompensas", e);
        }
    }

    public Optional<Reward> findById(Long id) {
        try {
            return rewardRepository.findById(id);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao buscar recompensa por ID", e);
        }
    }
}
