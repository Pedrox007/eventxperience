package com.project.eventxperience.musicevent.model.dto.response;

import com.project.eventxperience.framework.model.dto.base.BaseDTO;
import com.project.eventxperience.musicevent.model.MusicEvent;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class MusicEventResponseDTO implements BaseDTO<MusicEvent> {
    private Long id;
    private String name;
    private String description;
    private Date eventDate;
    private Integer ticketQuantity;
    private Float ticketPrice;
    private Long creatorId;
    private List<BandResponseDTO> bands = new ArrayList<>();

    @Override
    public MusicEvent parseToEntity() {
        MusicEvent musicEvent = new MusicEvent();
        musicEvent.setName(name);
        musicEvent.setDescription(description);
        musicEvent.setEventDate(eventDate);
        musicEvent.setTicketQuantity(ticketQuantity);
        musicEvent.setTicketPrice(ticketPrice);
        return musicEvent;
    }

    @Override
    public void parseToDTO(MusicEvent musicEvent) {
        setId(musicEvent.getId());
        setName(musicEvent.getName());
        setDescription(musicEvent.getDescription());
        setEventDate(musicEvent.getEventDate());
        setTicketQuantity(musicEvent.getTicketQuantity());
        setTicketPrice(musicEvent.getTicketPrice());
        setCreatorId(musicEvent.getCreator().getId());

        musicEvent.getBands().forEach(band -> {
            BandResponseDTO bandDTO = new BandResponseDTO();
            bandDTO.parseToDTO(band);
            bands.add(bandDTO);
        });
    }
}
