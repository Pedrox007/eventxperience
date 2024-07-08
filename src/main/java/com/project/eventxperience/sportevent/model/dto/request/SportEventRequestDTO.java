package com.project.eventxperience.sportevent.model.dto.request;

import com.project.eventxperience.framework.model.dto.base.BaseDTO;
import com.project.eventxperience.sportevent.model.SportEvent;
import lombok.Data;

import java.util.Date;

@Data
public class SportEventRequestDTO implements BaseDTO<SportEvent> {
    private String name;
    private String description;
    private Date eventDate;
    private Integer ticketQuantity;
    private Float ticketPrice;
    private Long sportId;

    @Override
    public SportEvent parseToEntity() {
        SportEvent sportEvent = new SportEvent();
        sportEvent.setName(name);
        sportEvent.setDescription(description);
        sportEvent.setEventDate(eventDate);
        sportEvent.setTicketQuantity(ticketQuantity);
        sportEvent.setTicketPrice(ticketPrice);
        return sportEvent;
    }

    @Override
    public void parseToDTO(SportEvent sportEvent) {
        setName(sportEvent.getName());
        setDescription(sportEvent.getDescription());
        setEventDate(sportEvent.getEventDate());
        setTicketQuantity(sportEvent.getTicketQuantity());
        setTicketPrice(sportEvent.getTicketPrice());
        setSportId(sportEvent.getSport().getId());
    }
}
