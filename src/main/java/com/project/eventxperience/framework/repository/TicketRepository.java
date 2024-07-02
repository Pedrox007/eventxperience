package com.project.eventxperience.framework.repository;

import com.project.eventxperience.framework.model.Event;
import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.model.Ticket;
import com.project.eventxperience.sportevent.model.SportEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket findByEventIdAndUserId(Long eventId, Long userId);
    int countByEventIdAndConfirmedTrue(Long eventId);
    @Query("SELECT DISTINCT t.event FROM Ticket t WHERE t.user = :user AND t.confirmed = true")
    List<Event> findEventsByUser(@Param("user") User user);
}
