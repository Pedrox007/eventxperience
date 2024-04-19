package com.project.eventxperience.repository;

import com.project.eventxperience.model.Sport;
import com.project.eventxperience.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {}

