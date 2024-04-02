package com.project.eventxperience.repository;

import com.project.eventxperience.model.User;
import org.springframework.data.repository.CrudRepository;

public interface SportEventRepository extends CrudRepository<User, Long> {}