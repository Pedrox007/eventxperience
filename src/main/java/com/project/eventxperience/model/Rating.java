package com.project.eventxperience.model;

import com.project.eventxperience.enums.RatingValues;
import jakarta.persistence.*;

@Entity
@Table(name="db_rating")
public class Rating {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private RatingValues rating;
}
