package com.project.eventxperience.framework.controller;

import com.project.eventxperience.framework.model.Claim;
import com.project.eventxperience.framework.model.Reward;
import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.model.dto.RewardDTO;
import com.project.eventxperience.framework.service.RewardService;
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
        Reward savedReward = rewardService.saveOrUpdate(reward);
        return ResponseEntity.ok(savedReward);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Reward> getRewardById(@PathVariable Long id) {
        Optional<Reward> reward = rewardService.findById(id);
        return reward.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reward> updateReward(@PathVariable Long id, @RequestBody Reward reward) {
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
    }


    @GetMapping("/all")
    public ResponseEntity<List<Reward>> getAllRewards() {
        List<Reward> rewards = rewardService.findAll();
        return ResponseEntity.ok(rewards);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReward(@PathVariable Long id) {
            rewardService.deleteById(id);
            return ResponseEntity.noContent().build();
    }

    @PostMapping("/claim")
    public ResponseEntity<Long> requestReward(@RequestBody RewardDTO requestRewardDTO, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Long claimId = rewardService.requestReward(user, requestRewardDTO);
        return ResponseEntity.ok(claimId);
    }
    @PostMapping("/confirm/{claimId}")
    public ResponseEntity<?> confirmReward(@PathVariable Long claimId, Authentication authentication) {
        User organizer = (User) authentication.getPrincipal();
        Claim confirmedClaim = rewardService.confirmReward(claimId, organizer);
        return ResponseEntity.ok(confirmedClaim);
    }
}

