package com.project.eventxperience.seminarevent.model.dto.request;

import com.project.eventxperience.framework.model.dto.base.BaseDTO;
import com.project.eventxperience.seminarevent.model.SeminarEvent;
import com.project.eventxperience.seminarevent.model.Speaker;
import lombok.Data;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class SeminarEventRequestDTO implements BaseDTO<SeminarEvent> {
    private String name;
    private String description;
    private Date eventDate;
    private Integer ticketQuantity;
    private Float ticketPrice;
    private String theme;
    private Set<Long> speakerIds;

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
        setName(seminarEvent.getName());
        setDescription(seminarEvent.getDescription());
        setEventDate(seminarEvent.getEventDate());
        setTicketQuantity(seminarEvent.getTicketQuantity());
        setTicketPrice(seminarEvent.getTicketPrice());
        setTheme(seminarEvent.getTheme());
        setSpeakerIds(seminarEvent.getSpeakers().stream().map(Speaker::getId).collect(Collectors.toSet()));
    }
}
