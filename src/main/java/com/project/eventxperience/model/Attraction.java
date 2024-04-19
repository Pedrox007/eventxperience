package com.project.eventxperience.model;

import com.project.eventxperience.model.base.BaseModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="db_attraction")
public class Attraction extends BaseModel {

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "attractions")
    private List<Attraction> attractions;
}
