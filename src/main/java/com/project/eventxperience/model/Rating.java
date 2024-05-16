package com.project.eventxperience.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.eventxperience.model.base.BaseModel;
import com.project.eventxperience.model.enums.RatingValues;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="db_rating")
@Data
public class Rating extends BaseModel {
    @Enumerated(EnumType.STRING)
    private RatingValues rating;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="attraction_id")
    private Attraction attraction;

}
