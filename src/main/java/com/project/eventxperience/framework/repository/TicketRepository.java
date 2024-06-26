package com.project.eventxperience.framework.repository;

import com.project.eventxperience.framework.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket findByEventIdAndUserId(Long eventId, Long userId);
    int countByEventIdAndConfirmedTrue(Long eventId);
}

