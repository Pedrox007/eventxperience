package com.project.eventxperience.musicevent.model.dto;

import com.project.eventxperience.framework.model.dto.base.BaseDTO;
import com.project.eventxperience.musicevent.model.Band;
import com.project.eventxperience.musicevent.model.enums.MusicStyleValue;
import lombok.Data;

@Data
public class BandDTO implements BaseDTO<Band> {
    private Long id;
    private String name;
    private MusicStyleValue musicStyle;

    @Override
    public Band parseToEntity() {
        Band band = new Band();
        band.setId(id);
        band.setName(name);
        band.setMusicStyle(musicStyle);

        return band;
    }

    @Override
    public void parseToDTO(Band band) {
        setId(band.getId());
        setName(band.getName());
        setMusicStyle(band.getMusicStyle());
    }
}
