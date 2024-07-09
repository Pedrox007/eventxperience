package com.project.eventxperience.sportevent.model.dto.response;

import com.project.eventxperience.framework.model.dto.UserDTO;
import com.project.eventxperience.framework.model.dto.base.BaseDTO;
import com.project.eventxperience.sportevent.model.SportEvent;
import lombok.Data;

import java.util.Date;

@Data
public class SportEventResponseDTO implements BaseDTO<SportEvent> {
    private Long id;
    private String name;
    private String description;
    private Date eventDate;
    private Integer ticketQuantity;
    private Float ticketPrice;
    private SportResponseDTO sport;
    private Long creatorId;

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
        setId(sportEvent.getId());
        setName(sportEvent.getName());
        setDescription(sportEvent.getDescription());
        setEventDate(sportEvent.getEventDate());
        setTicketQuantity(sportEvent.getTicketQuantity());
        setTicketPrice(sportEvent.getTicketPrice());
        setCreatorId(sportEvent.getCreator().getId());

        SportResponseDTO sportDTO = new SportResponseDTO();
        sportDTO.setId(sportEvent.getSport().getId());
        sportDTO.setName(sportEvent.getSport().getName());
        sportDTO.setDescription(sportEvent.getSport().getDescription());
        sportDTO.setParticipants(sportEvent.getSport().getParticipants());
        setSport(sportDTO);
    }
}