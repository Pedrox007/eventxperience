package com.project.eventxperience.framework.model;

import com.project.eventxperience.framework.model.base.BaseModel;
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

    @Column
    private int price;

    @ManyToMany
    @JoinTable(
            name = "event_reward",
            joinColumns = @JoinColumn(name = "reward_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    protected List<Event> events;

    @OneToMany (mappedBy = "reward")
    List<Claim> claims = new ArrayList<>();
}
