package com.project.eventxperience.musicevent.model;

import com.project.eventxperience.framework.model.Attraction;
import com.project.eventxperience.musicevent.model.enums.MusicStyleValue;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "db_band")
@PrimaryKeyJoinColumn(referencedColumnName="id")
@Data
public class Band extends Attraction {
    @Enumerated(EnumType.STRING)
    private MusicStyleValue musicStyle;
}
