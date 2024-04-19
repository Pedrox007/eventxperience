package com.project.eventxperience.model;

import com.project.eventxperience.model.base.BaseModel;
import com.project.eventxperience.model.base.Event;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="db_attraction")
@Data
public class Attraction extends BaseModel {
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "attraction")
    protected List<Rating> ratings;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private SportEvent sportEvent;
}
