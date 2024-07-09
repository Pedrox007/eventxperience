package com.project.eventxperience.seminarevent.model.dto.response;


import com.project.eventxperience.framework.model.dto.base.BaseDTO;
import com.project.eventxperience.seminarevent.model.Speaker;
import lombok.Data;

@Data
public class SpeakerResponseDTO implements BaseDTO<Speaker> {
    private Long id;
    private String name;
    private String expertise;

    @Override
    public Speaker parseToEntity() {
        Speaker speaker = new Speaker();
        speaker.setId(id);
        speaker.setName(name);
        speaker.setExpertise(expertise);

        return speaker;
    }

    @Override
    public void parseToDTO(Speaker speaker) {
        setId(speaker.getId());
        setName(speaker.getName());
        setExpertise(speaker.getExpertise());
    }
}
