package com.project.eventxperience.musicevent.model;

import com.project.eventxperience.framework.model.Event;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "db_music_event")
@Data
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class MusicEvent extends Event {
    @ManyToMany
    @JoinTable(
            name = "musicevent_band",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "band_id")
    )
    private List<Band> bands;
}
