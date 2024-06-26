package com.project.eventxperience.framework.model.dto;
public class RewardDTO {

    private Long rewardId;


    public RewardDTO() {
    }

    public RewardDTO(Long rewardId) {
        this.rewardId = rewardId;
    }

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }
}

