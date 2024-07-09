package com.project.eventxperience.seminarevent.model;

import com.project.eventxperience.framework.model.Event;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "db_seminare_event")
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class SeminarEvent extends Event {

    @Column(name = "theme")
    private String theme;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "seminar_event_speaker",
            joinColumns = @JoinColumn(name = "seminar_event_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id")
    )
    private List<Speaker> speakers;
}

