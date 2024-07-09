package com.project.eventxperience.seminarevent.model.dto.response;

import com.project.eventxperience.framework.model.dto.base.BaseDTO;
import com.project.eventxperience.seminarevent.model.SeminarEvent;
import lombok.Data;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class SeminarEventResponseDTO implements BaseDTO<SeminarEvent> {
    private Long id;
    private String name;
    private String description;
    private Date eventDate;
    private Integer ticketQuantity;
    private Float ticketPrice;
    private String theme;
    private Set<SpeakerResponseDTO> speakers;
    private Long creatorId;

    @Override
    public SeminarEvent parseToEntity() {
        SeminarEvent seminarEvent = new SeminarEvent();
        seminarEvent.setName(name);
        seminarEvent.setDescription(description);
        seminarEvent.setEventDate(eventDate);
        seminarEvent.setTicketQuantity(ticketQuantity);
        seminarEvent.setTicketPrice(ticketPrice);
        seminarEvent.setTheme(theme);
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
        setTheme(seminarEvent.getTheme());
        setCreatorId(seminarEvent.getCreator().getId());

        setSpeakers(seminarEvent.getSpeakers().stream().map(speaker -> {
            SpeakerResponseDTO speakerDTO = new SpeakerResponseDTO();
            speakerDTO.parseToDTO(speaker);
            return speakerDTO;
        }).collect(Collectors.toSet()));
    }
}
