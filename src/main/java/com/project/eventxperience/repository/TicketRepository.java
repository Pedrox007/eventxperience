package com.project.eventxperience.repository;

import com.project.eventxperience.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket findBySportEventIdAndUserId(Long eventId, Long userId);

}

