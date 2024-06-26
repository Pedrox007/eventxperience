package com.project.eventxperience.framework.model;

import com.project.eventxperience.framework.model.User;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
@Inheritance( strategy = InheritanceType.JOINED )
@Data
public class Event extends BaseModel {
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
    @ManyToOne
    protected User creator;
}
