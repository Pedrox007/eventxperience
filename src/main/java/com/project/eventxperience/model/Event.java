package com.project.eventxperience.model;

import java.util.Date;

public abstract class Event {
    protected Integer id;
    protected String name;
    protected Date eventDate;
    protected String description;
    protected Integer ticketQuantity;
    protected Float ticketPrice;
    protected Date createdOn;
    protected Date updatedOn;

    public Event(int id, String name, Date eventDate, String description) {
    }

    public Event(int id, String name, Date eventDate, String description, int ticketQuantity, float ticketPrice, Date createdOn, Date updatedOn) {
    }

    public abstract float calculateRateAverage();
    public abstract int calculateTicketsAvailability();
    public abstract void confirmPresence(User user);

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public String getDescription() {
        return description;
    }

    public Integer getTicketQuantity() {
        return ticketQuantity;
    }

    public Float getTicketPrice() {
        return ticketPrice;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTicketQuantity(Integer ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }

    public void setTicketPrice(Float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
