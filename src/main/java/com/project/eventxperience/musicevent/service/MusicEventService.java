package com.project.eventxperience.musicevent.service;

import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.service.base.EventServiceInterface;
import com.project.eventxperience.musicevent.model.Band;
import com.project.eventxperience.musicevent.model.MusicEvent;
import com.project.eventxperience.musicevent.model.dto.request.MusicEventRequestDTO;
import com.project.eventxperience.musicevent.model.dto.response.MusicEventResponseDTO;
import com.project.eventxperience.musicevent.repository.BandRepository;
import com.project.eventxperience.musicevent.repository.MusicEventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MusicEventService implements EventServiceInterface<MusicEventRequestDTO, MusicEventResponseDTO> {
    @Autowired
    private MusicEventRepository musicEventRepository;
    @Autowired
    private BandRepository bandRepository;

    @Override
    public MusicEventResponseDTO saveEvent(User authenticatedUser, MusicEventRequestDTO musicEventRequestDTO) {
        MusicEvent musicEvent = musicEventRequestDTO.parseToEntity();

        List<Band> bands = validateBands(musicEventRequestDTO);
        musicEvent.setBands(bands);
        musicEvent.setCreator(authenticatedUser);

        musicEventRepository.save(musicEvent);

        MusicEventResponseDTO musicEventResponseDTO = new MusicEventResponseDTO();
        musicEventResponseDTO.parseToDTO(musicEvent);

        return musicEventResponseDTO;
    }

    @Override
    public MusicEventResponseDTO retrieveEventById(Long id) {
        MusicEvent musicEvent = musicEventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Music event does not exist!"));

        MusicEventResponseDTO musicEventResponseDTO = new MusicEventResponseDTO();
        musicEventResponseDTO.parseToDTO(musicEvent);

        return musicEventResponseDTO;
    }

    @Override
    public MusicEventResponseDTO deleteEvent(Long id) {
        MusicEvent musicEvent = musicEventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Music event does not exist!"));

        musicEventRepository.deleteById(id);

        MusicEventResponseDTO musicEventResponseDTO = new MusicEventResponseDTO();
        musicEventResponseDTO.parseToDTO(musicEvent);

        return musicEventResponseDTO;
    }

    @Override
    public MusicEventResponseDTO updateEvent(User authenticatedUser, MusicEventRequestDTO musicEventRequestDTO, Long id) {
        if (musicEventRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Music Event not found!");
        }

        MusicEvent musicEvent = musicEventRequestDTO.parseToEntity();
        musicEvent.setId(id);
        musicEvent.setCreator(authenticatedUser);

        List<Band> bands = validateBands(musicEventRequestDTO);
        musicEvent.setBands(bands);

        musicEventRepository.save(musicEvent);

        MusicEventResponseDTO musicEventResponseDTO = new MusicEventResponseDTO();
        musicEventResponseDTO.parseToDTO(musicEvent);

        return musicEventResponseDTO;
    }

    private List<Band> validateBands(MusicEventRequestDTO musicEventRequestDTO) {
        List<Band> bands = new ArrayList<>();
        musicEventRequestDTO.getBands().forEach(bandDTO -> {
            Band band = new Band();
            if (bandDTO.getId() != null) {
                band = bandRepository.findById(bandDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Band with id " + bandDTO.getId() + " does not exist!"));
            } else {
                band.setName(bandDTO.getName());
                band.setMusicGenre(bandDTO.getMusicGenre());
                band = bandRepository.save(band);
            }
            bands.add(band);
        });

        return bands;
    }

    @Override
    public List<MusicEventResponseDTO> listEvent() {
        Iterable<MusicEvent> events = musicEventRepository.findAll();
        List<MusicEvent> musicEvents = new ArrayList<>();
        events.forEach(musicEvents::add);

        List<MusicEventResponseDTO> musicEventResponseDTOS = new ArrayList<>();
        musicEvents.forEach(musicEvent -> {
            MusicEventResponseDTO musicEventResponseDTO = new MusicEventResponseDTO();
            musicEventResponseDTO.parseToDTO(musicEvent);
            musicEventResponseDTOS.add(musicEventResponseDTO);
        });

        return musicEventResponseDTOS;
    }
}