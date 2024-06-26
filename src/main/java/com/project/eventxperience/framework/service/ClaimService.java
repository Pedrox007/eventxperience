package com.project.eventxperience.framework.service;

import com.project.eventxperience.framework.model.Claim;
import com.project.eventxperience.framework.repository.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;

    @Autowired
    public ClaimService(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    public Claim saveClaim(Claim claim) {
        try {
            return claimRepository.save(claim);
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao salvar a reivindicação.", e);
        }
    }

    public Optional<Claim> findById(Long id) {
        try {
            return claimRepository.findById(id);
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao buscar reivindicação por ID.", e);
        }
    }
}

