package com.project.eventxperience.musicevent.model.dto.response;

import com.project.eventxperience.framework.model.dto.base.BaseDTO;
import com.project.eventxperience.musicevent.model.Band;
import com.project.eventxperience.musicevent.model.enums.MusicGenreValue;
import lombok.Data;

@Data
public class BandResponseDTO implements BaseDTO<Band> {
    private Long id;
    private String name;
    private MusicGenreValue musicGenre;

    @Override
    public Band parseToEntity() {
        Band band = new Band();
        band.setId(id);
        band.setName(name);
        band.setMusicGenre(musicGenre);

        return band;
    }

    @Override
    public void parseToDTO(Band band) {
        setId(band.getId());
        setName(band.getName());
        setMusicGenre(band.getMusicGenre());
    }
}
