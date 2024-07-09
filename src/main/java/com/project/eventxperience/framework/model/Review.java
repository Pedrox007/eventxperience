package com.project.eventxperience.framework.model;

import com.project.eventxperience.framework.model.base.BaseModel;
import com.project.eventxperience.framework.model.enums.ReviewValues;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="db_review")
@Data
public class Review extends BaseModel {
    @Enumerated(EnumType.STRING)
    private ReviewValues review;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="attraction_id")
    private Attraction attraction;

    @ManyToOne
    @JoinColumn(name="event_id")
    private Event event;
}
