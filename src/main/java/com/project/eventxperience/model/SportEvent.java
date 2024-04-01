package com.project.eventxperience.model;
import java.util.Date;

public class SportEvent extends Event {
    private Sport sport;

    public SportEvent(int id, String name, Date eventDate, String description, int ticketQuantity, float ticketPrice,
                      Date createdOn, Date updatedOn, Sport sport) {
        super(id, name, eventDate, description, ticketQuantity, ticketPrice, createdOn, updatedOn);
        this.sport = sport;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    @Override
    public float calculateRateAverage() {
        return 0;
    }

    @Override
    public int calculateTicketsAvailability() {
        return 0;
    }

    @Override
    public void confirmPresence(User user) {

    }
}
