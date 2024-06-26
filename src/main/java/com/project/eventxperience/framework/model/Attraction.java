package com.project.eventxperience.framework.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.eventxperience.framework.model.base.BaseModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="db_attraction")
@Inheritance( strategy = InheritanceType.JOINED )
@Data
public class Attraction extends BaseModel {
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "attraction", fetch = FetchType.EAGER)
    @JsonBackReference
    protected List<Review> ratings;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Event event;
}
