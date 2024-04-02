package com.project.eventxperience.model;
import com.project.eventxperience.model.base.Event;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "db_sport_event")
public class SportEvent extends Event {
    @ManyToOne
    @JoinColumn(name = "sport_id")
    private Sport sport;

    public SportEvent() {
    }

    public SportEvent(Sport sport) {
        this.sport = sport;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }
}
