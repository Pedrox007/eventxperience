package com.project.eventxperience.seminarevent.repository;

import com.project.eventxperience.seminarevent.model.Speaker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpeakerRepository extends CrudRepository<Speaker, Long>{
    Optional<Speaker> findByName(String s);
}
