package com.project.eventxperience.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(mappedBy = "attraction", fetch = FetchType.EAGER)
    protected List<Rating> ratings;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id")
    @JsonBackReference
    private SportEvent sportEvent;
}
