package com.project.eventxperience.musicevent.model;

import com.project.eventxperience.framework.model.Event;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "db_music_event")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class MusicEvent extends Event {

    @ManyToMany
    @JoinTable(
            name = "music_event_band",
            joinColumns = @JoinColumn(name = "music_event_id"),
            inverseJoinColumns = @JoinColumn(name = "band_id")
    )
    private List<Band> bands;
}
