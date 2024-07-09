package com.project.eventxperience.sportevent.repository;

import com.project.eventxperience.sportevent.model.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SportRepository extends CrudRepository<Sport, Long> {
    Optional<Sport> findByName(String name);
}
