package com.project.eventxperience.seminarevent.model;

import com.project.eventxperience.framework.model.Attraction;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "db_speaker")
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class Speaker extends Attraction {

    @Column(name = "expertise")
    private String expertise;
}
