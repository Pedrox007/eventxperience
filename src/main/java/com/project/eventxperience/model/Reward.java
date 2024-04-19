package com.project.eventxperience.model;

import com.project.eventxperience.model.base.BaseModel;
import com.project.eventxperience.model.base.Event;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "db_reward")
@Data
public class Reward extends BaseModel {

    @Column
    private String name;

    @Column
    private String description;

    @ManyToMany
    @JoinTable(
            name = "event_reward",
            joinColumns = @JoinColumn(name = "reward_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    protected List<SportEvent> sportEvents;

    @OneToMany (mappedBy = "reward")
    List<Claim> claims = new ArrayList<>();
}
