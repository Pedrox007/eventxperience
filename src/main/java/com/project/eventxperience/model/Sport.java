package com.project.eventxperience.model;

import com.project.eventxperience.model.base.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "db_sport")
@Data
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
}