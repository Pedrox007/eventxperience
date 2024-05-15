package com.project.eventxperience.service;

import com.project.eventxperience.model.Sport;
import com.project.eventxperience.repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportService {

    private final SportRepository sportRepository;

    @Autowired
    public SportService(SportRepository sportRepository) {

        this.sportRepository = sportRepository;
    }

    public Sport addSport(Sport sport) {
        try {
            return sportRepository.save(sport);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao adicionar esporte", e);
        }
    }

    public List<Sport> getAllSports() {
        try {
            return sportRepository.findAll();
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao obter todos os esportes", e);
        }
    }
}