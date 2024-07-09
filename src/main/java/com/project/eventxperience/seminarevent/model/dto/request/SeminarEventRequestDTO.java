package com.project.eventxperience.seminarevent.model.dto.request;

import com.project.eventxperience.framework.model.dto.base.BaseDTO;
import com.project.eventxperience.seminarevent.model.SeminarEvent;
import com.project.eventxperience.seminarevent.model.dto.SpeakerDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class SeminarEventRequestDTO implements BaseDTO<SeminarEvent> {
    private Long id;
    private String name;
    private String description;
    private Date eventDate;
    private Integer ticketQuantity;
    private Float ticketPrice;
    private List<SpeakerDTO> speakers = new ArrayList<>();

    @Override
    public SeminarEvent parseToEntity() {
        SeminarEvent seminarEvent = new SeminarEvent();
        seminarEvent.setName(name);
        seminarEvent.setDescription(description);
        seminarEvent.setEventDate(eventDate);
        seminarEvent.setTicketQuantity(ticketQuantity);
        seminarEvent.setTicketPrice(ticketPrice);

        return seminarEvent;
    }

    @Override
    public void parseToDTO(SeminarEvent seminarEvent) {
        setId(seminarEvent.getId());
        setName(seminarEvent.getName());
        setDescription(seminarEvent.getDescription());
        setEventDate(seminarEvent.getEventDate());
        setTicketQuantity(seminarEvent.getTicketQuantity());
        setTicketPrice(seminarEvent.getTicketPrice());

        seminarEvent.getSpeakers().forEach(speaker -> {
            SpeakerDTO speakerDTO = new SpeakerDTO();
            speakerDTO.parseToDTO(speaker);
            speakers.add(speakerDTO);
        });
    }
}
