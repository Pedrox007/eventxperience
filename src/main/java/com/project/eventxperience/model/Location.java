package com.project.eventxperience.model;

import com.project.eventxperience.model.base.BaseModel;
import jakarta.persistence.*;

@Entity
@Table(name = "db_location")
public class Location extends BaseModel {

    @Column
    private String latitude;

    @Column
    private String longitude;
}
