package com.project.eventxperience.framework.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.project.eventxperience.framework.model.base.BaseModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="db_ticket")
@Data
public class Ticket extends BaseModel {
    @Column(nullable = false)
    private float price;

    @Column
    private boolean confirmed;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIdentityReference
    private User user;

    @ManyToOne
    @JoinColumn(name="event_id")
    private Event event;
}
