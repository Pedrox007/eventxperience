package com.project.eventxperience.model;
import com.project.eventxperience.model.base.Event;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "db_sport_event")
@Data
public class SportEvent extends Event {
    @OneToOne
    @JoinColumn(name="location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "sport_id")
    private Sport sport;

    @ManyToMany
    @JoinTable(
            name = "sportevent_reward",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "reward_id")
    )
    protected List<Reward> rewards;

    @OneToMany (mappedBy = "sportEvent")
    List<Ticket> tickets = new ArrayList<>();

    @OneToMany(mappedBy = "sportEvent")
    protected List<Attraction> attractions;

    public SportEvent() {
    }

    public SportEvent(Sport sport) {
        this.sport = sport;
    }
}
