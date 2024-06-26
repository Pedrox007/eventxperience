package com.project.eventxperience.framework.service;

import com.project.eventxperience.framework.model.Attraction;
import com.project.eventxperience.framework.sportevent.model.SportEvent;
import com.project.eventxperience.framework.model.dto.AttractionDTO;
import com.project.eventxperience.framework.repository.AttractionRepository;
import com.project.eventxperience.framework.repository.SportEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttractionService {

    private final AttractionRepository attractionRepository;
    private final SportEventRepository sportEventRepository;

    @Autowired
    public AttractionService(AttractionRepository attractionRepository, SportEventRepository sportEventRepository) {
        this.attractionRepository = attractionRepository;
        this.sportEventRepository = sportEventRepository;
    }

    public List<Attraction> saveAttractions(Long eventId,List<AttractionDTO> attractionsDTO) {
        List<Attraction> attractions = new ArrayList<>();

        try{
            Optional<SportEvent> event = sportEventRepository.findById(eventId);

            if (event.isEmpty()) {
                throw new IllegalArgumentException("Evento não encontrado");
            }

            for (AttractionDTO attractionDTO : attractionsDTO) {
                Attraction attraction = new Attraction();
                attraction.setId(attractionDTO.getId());
                attraction.setName(attractionDTO.getName());
                attraction.setSportEvent(event.get());
                attractions.add(attraction);
            }

            return Streamable.of(attractionRepository.saveAll(attractions)).toList();
        }catch (DataAccessException e){
            throw new IllegalStateException("Erro ao salvar atrações", e);
        }catch (Exception e){
            throw new IllegalStateException("Erro desconhecido ao salvar atrações no banco de dados", e);
        }
    }

    public List<Attraction> getAttractions(Long eventId) {
        try {
            return attractionRepository.findAllByEventId(eventId);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao obter atrações do banco de dados", e);
        }
    }

    @Transactional
    public List<Attraction> deleteAttractions(Long id, List<Long> attractionsIds) {
        List<Attraction> deletedAttractions = new ArrayList<>();
        Attraction attraction;

        try {
            for (Long attractionId : attractionsIds) {
                attraction = attractionRepository.findById(attractionId).orElseThrow(() -> new IllegalArgumentException("Atração não encontrada"));
                if (!attraction.getSportEvent().getId().equals(id)) {
                    throw new IllegalArgumentException("Atração não encontrada nesse evento");
                }
                attractionRepository.deleteById(attractionId);
                deletedAttractions.add(attraction);
            }

            return deletedAttractions;
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao excluir atrações no banco de dados", e);
        }catch (Exception e){
            throw new IllegalStateException("Erro desconhecido ao excluir atrações", e);
        }
    }
}