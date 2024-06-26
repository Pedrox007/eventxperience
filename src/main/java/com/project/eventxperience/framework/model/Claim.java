package com.project.eventxperience.framework.model;

import com.project.eventxperience.framework.model.base.BaseModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="db_claim")
@Data
public class Claim extends BaseModel {
    @Column
    private boolean confirmed;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="reward_id")
    private Reward reward;

    public Claim() {
    }
}
