package com.project.eventxperience.framework.model;

import com.project.eventxperience.framework.model.base.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "db_permission")
@Data
public class Permission extends BaseModel {
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
