package com.project.eventxperience.framework.repository;

import com.project.eventxperience.framework.model.Attraction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttractionRepository extends CrudRepository<Attraction, Long> {
}