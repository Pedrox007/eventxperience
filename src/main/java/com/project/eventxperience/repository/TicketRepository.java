package com.project.eventxperience.repository;

import com.project.eventxperience.model.SportEvent;
import com.project.eventxperience.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket findBySportEventIdAndUserId(Long eventId, Long userId);
    int countBySportEventIdAndConfirmedTrue(Long eventId);
}

