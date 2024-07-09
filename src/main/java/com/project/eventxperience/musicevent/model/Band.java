package com.project.eventxperience.musicevent.model;

import com.project.eventxperience.framework.model.Attraction;
import com.project.eventxperience.musicevent.model.enums.MusicGenreValue;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "db_band")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Band extends Attraction {
    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private MusicGenreValue musicGenre;

    @Column(name = "description")
    private String description;
}
