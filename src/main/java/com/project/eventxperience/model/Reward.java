package com.project.eventxperience.model;

import com.project.eventxperience.model.base.BaseModel;
import jakarta.persistence.*;

@Entity
@Table(name = "db_reward")
public class Reward extends BaseModel {

    @Column
    private String name;

    @Column
    private String description;


}
