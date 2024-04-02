package com.project.eventxperience.model.base;

import jakarta.persistence.*;

import java.util.Date;

@MappedSuperclass
public abstract class Event extends BaseModel {
    @Column(name = "name")
    protected String name;
    @Column(name = "event_date")
    protected Date eventDate;
    @Column(name = "description")
    protected String description;
    @Column(name = "ticket_quantity")
    protected Integer ticketQuantity;
    @Column(name = "ticket_price")
    protected Float ticketPrice;

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
}
