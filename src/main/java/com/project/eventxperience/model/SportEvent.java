package com.project.eventxperience.model;
import com.project.eventxperience.model.base.Event;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "db_sport_event")
public class SportEvent extends Event {
    @OneToOne
    @JoinColumn(name="location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "sport_id")
    private Sport sport;

    @OneToMany(mappedBy = "sport_event")
    List<Claim> claims = new ArrayList<>();

    @OneToMany (mappedBy = "user")
    List<Ticket> tickets = new ArrayList<>();

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
