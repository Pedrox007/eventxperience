package com.project.eventxperience.model;

import com.project.eventxperience.model.base.BaseModel;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name="db_ticket")
public class Ticket extends BaseModel {
    @Column(nullable = false)
    private float price;

    @Column
    private boolean confirmed;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="sport_event_id")
    private SportEvent sportevent;

}
