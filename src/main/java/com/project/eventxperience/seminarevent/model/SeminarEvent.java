package com.project.eventxperience.seminarevent.model;

import com.project.eventxperience.framework.model.Event;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "db_seminare_event")
@Data
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class SeminarEvent extends Event {
    @ManyToMany
    @JoinTable(
            name = "musicevent_speaker",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id")
    )
    private List<Speaker> speakers;
}
