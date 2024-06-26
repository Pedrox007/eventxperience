package com.project.eventxperience.framework.model.dto;

import jakarta.persistence.Column;

import java.util.Date;

public class SportEventDTO {
    private String name;
    private Date eventDate;
    private String description;
    private Integer ticketQuantity;
    private Float ticketPrice;
    private Long sport_id;
    private Long creator_id;

    public SportEventDTO() {
    }

    public SportEventDTO(String name, Date eventDate, String description, Integer ticketQuantity, Float ticketPrice, Long sport_id) {
        this.name = name;
        this.eventDate = eventDate;
        this.description = description;
        this.ticketQuantity = ticketQuantity;
        this.ticketPrice = ticketPrice;
        this.sport_id = sport_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTicketQuantity() {
        return ticketQuantity;
    }

    public void setTicketQuantity(Integer ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }

    public Float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Long getSport_id() {
        return sport_id;
    }

    public void setSport_id(Long sport_id) {
        this.sport_id = sport_id;
    }

    public Long getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(Long creator_id) {
        this.creator_id = creator_id;
    }
}
