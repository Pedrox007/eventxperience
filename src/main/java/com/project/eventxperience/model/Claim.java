package com.project.eventxperience.model;

import com.project.eventxperience.model.base.BaseModel;
import jakarta.persistence.*;


@Entity
@Table(name="db_claim")
public class Claim extends BaseModel {
    @Column
    private boolean confirmed;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="sport_event_id")
    private SportEvent sportEvent;
}
