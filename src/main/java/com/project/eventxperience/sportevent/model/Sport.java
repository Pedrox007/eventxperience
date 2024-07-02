package com.project.eventxperience.sportevent.model;

import com.project.eventxperience.framework.model.Attraction;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "db_sport")
@PrimaryKeyJoinColumn(referencedColumnName="id")
@Data
public class Sport extends Attraction {
    @Column(name = "participants")
    private Integer participants;
    @Column(name = "description")
    private String description;
}