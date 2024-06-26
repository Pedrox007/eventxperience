package com.project.eventxperience.framework.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.eventxperience.framework.model.base.BaseModel;
import com.project.eventxperience.framework.model.enums.ReviewValues;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="db_rating")
@Data
public class Review extends BaseModel {
    @Enumerated(EnumType.STRING)
    private ReviewValues rating;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="attraction_id")
    private Attraction attraction;

}
