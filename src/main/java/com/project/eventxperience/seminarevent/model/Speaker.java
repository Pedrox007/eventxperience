package com.project.eventxperience.seminarevent.model;

import com.project.eventxperience.framework.model.Attraction;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "db_speaker")
@PrimaryKeyJoinColumn(referencedColumnName="id")
@Data
public class Speaker extends Attraction {
    private String expertise;
}
