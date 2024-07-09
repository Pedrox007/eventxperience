package com.project.eventxperience.musicevent.repository;

import com.project.eventxperience.musicevent.model.Band;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BandRepository extends CrudRepository<Band, Long> {
    Optional<Band> findByName(String name);
}
