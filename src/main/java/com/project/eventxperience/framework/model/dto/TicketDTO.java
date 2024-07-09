package com.project.eventxperience.framework.model.dto;

import com.project.eventxperience.framework.model.Ticket;
import com.project.eventxperience.framework.model.dto.base.BaseDTO;
import lombok.Data;

@Data
public class TicketDTO implements BaseDTO<Ticket> {
    private Long id;
    private float price;
    private boolean confirmed;
    private Long userId;
    private Long eventId;

    @Override
    public Ticket parseToEntity() {
        Ticket ticket = new Ticket();
        ticket.setId(id);
        ticket.setPrice(price);
        ticket.setConfirmed(confirmed);

        return ticket;
    }

    @Override
    public void parseToDTO(Ticket ticket) {
        id = ticket.getId();
        price = ticket.getPrice();
        confirmed = ticket.isConfirmed();
        userId = ticket.getUser().getId();
        eventId = ticket.getEvent().getId();
    }
}
