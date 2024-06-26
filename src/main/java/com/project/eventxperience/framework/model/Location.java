package com.project.eventxperience.framework.model;

import com.project.eventxperience.framework.model.base.BaseModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "db_location")
@Data
public class Location extends BaseModel {
    @Column
    private String latitude;

    @Column
    private String longitude;
}
