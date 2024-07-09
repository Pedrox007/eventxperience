package com.project.eventxperience.sportevent.model;

import com.project.eventxperience.framework.model.Event;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "db_sport_event")
@Data
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class SportEvent extends Event {
    @ManyToOne
    @JoinColumn(name = "sport_id")
    private Sport sport;

    public SportEvent() {
    }

    public SportEvent(Sport sport) {
        this.sport = sport;
    }

}