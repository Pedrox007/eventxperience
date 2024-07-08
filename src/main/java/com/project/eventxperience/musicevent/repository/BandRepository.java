package com.project.eventxperience.musicevent.repository;

import com.project.eventxperience.musicevent.model.Band;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BandRepository extends CrudRepository<Band, Long> {

    Optional<Band> findByName(String name);
}
