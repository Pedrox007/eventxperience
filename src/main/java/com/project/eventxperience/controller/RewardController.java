package com.project.eventxperience.controller;

import com.project.eventxperience.model.Claim;
import com.project.eventxperience.model.Reward;
import com.project.eventxperience.model.User;
import com.project.eventxperience.model.dto.RewardDTO;
import com.project.eventxperience.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rewards")
public class RewardController {

    private final RewardService rewardService;

    @Autowired
    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @PostMapping("/add")
    public ResponseEntity<Reward> addReward(@RequestBody Reward reward) {
        try {
            Reward savedReward = rewardService.saveOrUpdate(reward);
            return ResponseEntity.ok(savedReward);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Reward> getRewardById(@PathVariable Long id) {
        Optional<Reward> reward = rewardService.findById(id);
        return reward.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reward> updateReward(@PathVariable Long id, @RequestBody Reward reward) {
        try {
            Optional<Reward> existingRewardOptional = rewardService.findById(id);

            if (existingRewardOptional.isPresent()) {
                Reward existingReward = existingRewardOptional.get();
                existingReward.setName(reward.getName());
                existingReward.setDescription(reward.getDescription());
                existingReward.setPrice(reward.getPrice());
                existingReward.setSportEvents(reward.getSportEvents());
                existingReward.setClaims(reward.getClaims());
                Reward updatedReward = rewardService.saveOrUpdate(existingReward);
                return ResponseEntity.ok(updatedReward);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/all")
    public ResponseEntity<List<Reward>> getAllRewards() {
        List<Reward> rewards = rewardService.findAll();
        return ResponseEntity.ok(rewards);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReward(@PathVariable Long id) {
        try {
            rewardService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/claim")
    public ResponseEntity<Long> requestReward(@RequestBody RewardDTO requestRewardDTO, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = (User) authentication.getPrincipal();

        try {
            rewardService.requestReward(user, requestRewardDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/confirm/{claimId}")
    public ResponseEntity<?> confirmReward(@PathVariable Long claimId, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User organizer = (User) authentication.getPrincipal();

        try {
            rewardService.confirmReward(claimId, organizer);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

