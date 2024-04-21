package com.project.eventxperience.repository;

import com.project.eventxperience.model.Attraction;
import com.project.eventxperience.model.SportEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttractionRepository extends CrudRepository<Attraction, Long> {
    @Query("SELECT a FROM Attraction a WHERE a.sportEvent.id = ?1")
    List<Attraction> findAllByEventId(Long eventId);
}