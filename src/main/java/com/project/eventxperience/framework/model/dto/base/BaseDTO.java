package com.project.eventxperience.framework.model.dto.base;

public interface BaseDTO<Entity> {
    Entity parseToEntity();
    void parseToDTO(Entity entity);
}
