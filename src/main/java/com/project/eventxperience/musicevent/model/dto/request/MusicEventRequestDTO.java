package com.project.eventxperience.musicevent.model.dto.request;

import com.project.eventxperience.framework.model.dto.base.BaseDTO;
import com.project.eventxperience.musicevent.model.MusicEvent;
import com.project.eventxperience.musicevent.model.dto.response.BandResponseDTO;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MusicEventRequestDTO implements BaseDTO<MusicEvent> {
    private Long id;
    private String name;
    private String description;
    private Date eventDate;
    private Integer ticketQuantity;
    private Float ticketPrice;
    private List<BandResponseDTO> bands;

    @Override
    public MusicEvent parseToEntity() {
        MusicEvent musicEvent = new MusicEvent();
        musicEvent.setName(name);
        musicEvent.setDescription(description);
        musicEvent.setEventDate(eventDate);
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

        musicEvent.getBands().forEach(band -> {
            BandResponseDTO bandDTO = new BandResponseDTO();
            bandDTO.parseToDTO(band);
            bands.add(bandDTO);
        });
    }
}
