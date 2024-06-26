package com.project.eventxperience.framework.service;

import com.project.eventxperience.framework.model.Claim;
import com.project.eventxperience.framework.model.Event;
import com.project.eventxperience.framework.model.Reward;
import com.project.eventxperience.framework.model.dto.RewardDTO;
import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.repository.RewardRepository;
import com.project.eventxperience.framework.utils.EventUtils;
import com.project.eventxperience.sportevent.model.SportEvent;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RewardService {

    private final RewardRepository rewardRepository;
    private final ClaimService claimService;

    @Autowired
    public RewardService(RewardRepository rewardRepository, ClaimService claimService) {
        this.rewardRepository = rewardRepository;
        this.claimService = claimService;
    }


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

    public Long requestReward(User user, RewardDTO rewardDTO) {
        try {
            // Buscar a recompensa pelo ID
            Reward reward = rewardRepository.findById(rewardDTO.getRewardId())
                    .orElseThrow(() -> new EntityNotFoundException("Recompensa não encontrada com o ID: " + rewardDTO.getRewardId()));

            // Criar uma nova solicitação de recompensa
            Claim claim = new Claim();
            claim.setUser(user);
            claim.setReward(reward);
            claim.setConfirmed(false); // Define como não confirmado inicialmente

            // Salvar a solicitação de recompensa
            Claim savedClaim = claimService.saveClaim(claim);

            return savedClaim.getId();
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao salvar a solicitação de recompensa", e);
        }
    }

    public Claim confirmReward(Long claimId, User organizer) {
        try {
            Claim claim = claimService.findById(claimId)
                    .orElseThrow(() -> new EntityNotFoundException("Reivindicação não encontrada com o ID: " + claimId));

            List<Event> events = claim.getReward().getEvents();

            // Verifica se o usuário é o organizador de pelo menos um dos eventos associados à recompensa
//            boolean isOrganizer = events.stream()
//                    .anyMatch(event -> EventUtils.isOrganizerOfEvent(event, organizer));

//            if (!isOrganizer) {
//                throw new IllegalStateException("O usuário não é um organizador de nenhum dos eventos associados à recompensa.");
//            }

            // Verifica se a reivindicação ainda não foi confirmada
            if (!claim.isConfirmed()) {
                // Confirma a reivindicação
                claim.setConfirmed(true);
                return claimService.saveClaim(claim);
            } else {
                throw new IllegalStateException("A recompensa já foi retirada.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao confirmar a recompensa: " + e.getMessage());
            throw new IllegalStateException("Erro ao confirmar a recompensa.", e);
        }
    }
}
