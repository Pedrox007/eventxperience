package com.project.eventxperience.model;

import com.project.eventxperience.model.base.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "db_sport")
public class Sport extends BaseModel {
    @Column(name = "name")
    private String name;
    @Column(name = "participants")
    private Integer participants;
    @Column(name = "description")
    private String description;

    public Sport() {
    }

    public Sport(String name, Integer participants, String description) {
        this.name = name;
        this.participants = participants;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}