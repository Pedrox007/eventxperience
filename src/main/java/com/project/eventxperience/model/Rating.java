package com.project.eventxperience.model;

import com.project.eventxperience.model.enums.RatingValues;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="db_rating")
@Data
public class Rating {
    @Id
    @GeneratedValue
    private int id;

    @Enumerated(EnumType.STRING)
    private RatingValues rating;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="attraction_id")
    private Attraction attraction;

}
