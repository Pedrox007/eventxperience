package com.project.eventxperience.framework.config;

import com.project.eventxperience.framework.model.*;
import com.project.eventxperience.framework.repository.*;
import com.project.eventxperience.musicevent.model.Band;
import com.project.eventxperience.musicevent.model.MusicEvent;
import com.project.eventxperience.musicevent.model.enums.MusicStyleValue;
import com.project.eventxperience.musicevent.repository.BandRepository;
import com.project.eventxperience.musicevent.repository.MusicEventRepository;
import com.project.eventxperience.seminarevent.model.SeminarEvent;
import com.project.eventxperience.seminarevent.model.Speaker;
import com.project.eventxperience.seminarevent.repository.SeminarEventRepository;
import com.project.eventxperience.seminarevent.repository.SpeakerRepository;
import com.project.eventxperience.seminarevent.service.SeminarEventService;
import com.project.eventxperience.sportevent.model.Sport;
import com.project.eventxperience.sportevent.model.SportEvent;
import com.project.eventxperience.sportevent.repository.SportEventRepository;
import com.project.eventxperience.sportevent.repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final SportRepository sportRepository;
    private final SportEventRepository sportEventRepository;
    private final MusicEventRepository musicEventRepository;
    private final BandRepository bandRepository;
    private final SeminarEventRepository seminarEventRepository;
    private final SpeakerRepository speakerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(RoleRepository roleRepository, UserRepository userRepository, SportRepository sportRepository, SportEventRepository sportEventRepository, MusicEventRepository musicEventRepository, BandRepository bandRepository, SeminarEventRepository seminarEventRepository, SpeakerRepository speakerRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.sportRepository = sportRepository;
        this.sportEventRepository = sportEventRepository;
        this.musicEventRepository = musicEventRepository;
        this.bandRepository = bandRepository;
        this.seminarEventRepository = seminarEventRepository;
        this.speakerRepository = speakerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Criar roles
        if (roleRepository.count() == 0) {
            Role roleUser = new Role();
            roleUser.setName("ROLE_USER");
            roleUser.setDescription("Usuário regular que frequenta os eventos");

            Role roleOrganizer = new Role();
            roleOrganizer.setName("ROLE_ORGANIZER");
            roleOrganizer.setDescription("Organizador de eventos");

            roleRepository.saveAll(Arrays.asList(roleUser, roleOrganizer));
        }

        if (userRepository.count() == 0) {
            // Criar usuários
            Optional<Role> roleUser = roleRepository.findByName("ROLE_USER");
            Optional<Role> roleOrganizer = roleRepository.findByName("ROLE_ORGANIZER");

            User user1 = new User();
            user1.setUsername("carol");
            user1.setEmail("carol@example.com");
            user1.setCpf("45709069078");
            user1.setPhone("991598681");
            user1.setPassword(passwordEncoder.encode("password1"));
            user1.setRoles(Arrays.asList(roleUser.orElse(null), roleOrganizer.orElse(null)));

            User user2 = new User();
            user2.setUsername("pedro");
            user2.setEmail("pedro@example.com");
            user2.setCpf("02009695011");
            user2.setPhone("991497681");
            user2.setPassword(passwordEncoder.encode("password2"));
            user2.setRoles(Collections.singletonList(roleOrganizer.orElse(null)));

            User user3 = new User();
            user3.setUsername("bruno");
            user3.setEmail("bruno@example.com");
            user3.setCpf("23656890005");
            user3.setPhone("991497681");
            user3.setPassword(passwordEncoder.encode("password3"));
            user3.setRoles(Collections.singletonList(roleUser.orElse(null)));

            userRepository.saveAll(Arrays.asList(user1, user2, user3));
        }

        // Criar esportes
        if (sportRepository.count() == 0) {
            Sport sport1 = new Sport();
            sport1.setName("Futebol");
            sport1.setDescription("Jogo com bola e dois times com 11 jogadores cada.");
            sport1.setParticipants(22);

            Sport sport2 = new Sport();
            sport2.setName("Basquete");
            sport2.setDescription("Jogo com bola e dois times com 5 jogadores cada.");
            sport2.setParticipants(10);

            sportRepository.saveAll(Arrays.asList(sport1, sport2));
        }

        // Criar eventos
        if (sportEventRepository.count() == 0) {
            Optional<User> user1 = userRepository.findByUsername("carol");
            Optional<User> user2 = userRepository.findByUsername("pedro");
            Optional<Sport> sport1 = sportRepository.findByName("Futebol");
            Optional<Sport> sport2 = sportRepository.findByName("Basquete");

            SportEvent firstEvent = new SportEvent();
            firstEvent.setName("Futebol na praia");
            firstEvent.setDescription("Jogo de futebol na praia");
            firstEvent.setTicketQuantity(10);
            firstEvent.setTicketPrice(10.0F);
            firstEvent.setCreator(user1.orElse(null));
            firstEvent.setSport(sport1.orElse(null));

            SportEvent secondEvent = new SportEvent();
            secondEvent.setName("Futebol no campo");
            secondEvent.setDescription("Jogo de futebol no campo");
            secondEvent.setTicketQuantity(20);
            secondEvent.setTicketPrice(20.0F);
            secondEvent.setCreator(user1.orElse(null));
            secondEvent.setSport(sport1.orElse(null));

            SportEvent thirdEvent = new SportEvent();
            thirdEvent.setName("Basquete na quadra");
            thirdEvent.setDescription("Jogo de basquete na quadra");
            thirdEvent.setTicketQuantity(10);
            thirdEvent.setTicketPrice(10.0F);
            thirdEvent.setCreator(user2.orElse(null));
            thirdEvent.setSport(sport2.orElse(null));

            SportEvent fourthEvent = new SportEvent();
            fourthEvent.setName("Basquete no parque");
            fourthEvent.setDescription("Jogo de basquete no parque");
            fourthEvent.setTicketQuantity(20);
            fourthEvent.setTicketPrice(20.0F);
            fourthEvent.setCreator(user2.orElse(null));
            fourthEvent.setSport(sport2.orElse(null));

            sportEventRepository.saveAll(Arrays.asList(firstEvent, secondEvent, thirdEvent, fourthEvent));
        }

        // Criar bandas
        if (bandRepository.count() == 0) {
            Band band1 = new Band();
            band1.setName("Banda 1");
            band1.setMusicStyle(MusicStyleValue.POP);

            Band band2 = new Band();
            band2.setName("Banda 2");
            band2.setMusicStyle(MusicStyleValue.ROCK);

            Band band3 = new Band();
            band3.setName("Banda 3");
            band3.setMusicStyle(MusicStyleValue.FORRO);

            Band band4 = new Band();
            band4.setName("Banda 4");
            band4.setMusicStyle(MusicStyleValue.SAMBA);

            Band band5 = new Band();
            band5.setName("Banda 5");
            band5.setMusicStyle(MusicStyleValue.PAGODE);

            bandRepository.saveAll(Arrays.asList(band1, band2, band3, band4, band5));
        }

        // Criar eventos de música
        if (musicEventRepository.count() == 0) {
            Optional<User> user1 = userRepository.findByUsername("carol");
            Optional<User> user2 = userRepository.findByUsername("pedro");
            Optional<Band> band1 = bandRepository.findByName("Banda 1");
            Optional<Band> band2 = bandRepository.findByName("Banda 2");
            Optional<Band> band3 = bandRepository.findByName("Banda 3");
            Optional<Band> band4 = bandRepository.findByName("Banda 4");
            Optional<Band> band5 = bandRepository.findByName("Banda 5");

            MusicEvent firstEvent = new MusicEvent();
            firstEvent.setName("Show de pop");
            firstEvent.setDescription("Show de pop com bandas locais");
            firstEvent.setTicketQuantity(10);
            firstEvent.setTicketPrice(10.0F);
            firstEvent.setCreator(user1.orElse(null));
            firstEvent.setBands(Arrays.asList(band1.orElse(null), band2.orElse(null)));

            MusicEvent secondEvent = new MusicEvent();
            secondEvent.setName("Show de rock");
            secondEvent.setDescription("Show de rock com bandas locais");
            secondEvent.setTicketQuantity(20);
            secondEvent.setTicketPrice(20.0F);
            secondEvent.setCreator(user1.orElse(null));
            secondEvent.setBands(Arrays.asList(band2.orElse(null), band3.orElse(null)));

            MusicEvent thirdEvent = new MusicEvent();
            thirdEvent.setName("Show de forró");
            thirdEvent.setDescription("Show de forró com bandas locais");
            thirdEvent.setTicketQuantity(10);
            thirdEvent.setTicketPrice(10.0F);
            thirdEvent.setCreator(user2.orElse(null));
            thirdEvent.setBands(Arrays.asList(band3.orElse(null), band4.orElse(null)));

            MusicEvent fourthEvent = new MusicEvent();
            fourthEvent.setName("Show de samba");
            fourthEvent.setDescription("Show de samba com bandas locais");
            fourthEvent.setTicketQuantity(20);
            fourthEvent.setTicketPrice(20.0F);
            fourthEvent.setCreator(user2.orElse(null));
            fourthEvent.setBands(Arrays.asList(band4.orElse(null), band5.orElse(null)));

            musicEventRepository.saveAll(Arrays.asList(firstEvent, secondEvent, thirdEvent, fourthEvent));
        }

        // Criar palestrantes
        if (speakerRepository.count() == 0) {
            Speaker speaker1 = new Speaker();
            speaker1.setName("Palestrante 1");
            speaker1.setExpertise("Tecnologia");

            Speaker speaker2 = new Speaker();
            speaker2.setName("Palestrante 2");
            speaker2.setExpertise("Negócios");

            Speaker speaker3 = new Speaker();
            speaker3.setName("Palestrante 3");
            speaker3.setExpertise("Marketing");

            Speaker speaker4 = new Speaker();
            speaker4.setName("Palestrante 4");
            speaker4.setExpertise("Vendas");

            Speaker speaker5 = new Speaker();
            speaker5.setName("Palestrante 5");
            speaker5.setExpertise("Finanças");

            speakerRepository.saveAll(Arrays.asList(speaker1, speaker2, speaker3, speaker4, speaker5));
        }

        // Criar eventos de seminário
        if (seminarEventRepository.count() == 0) {
            Optional<User> user1 = userRepository.findByUsername("carol");
            Optional<User> user2 = userRepository.findByUsername("pedro");
            Optional<Speaker> speaker1 = speakerRepository.findByName("Palestrante 1");
            Optional<Speaker> speaker2 = speakerRepository.findByName("Palestrante 2");
            Optional<Speaker> speaker3 = speakerRepository.findByName("Palestrante 3");
            Optional<Speaker> speaker4 = speakerRepository.findByName("Palestrante 4");
            Optional<Speaker> speaker5 = speakerRepository.findByName("Palestrante 5");

            SeminarEvent firstEvent = new SeminarEvent();
            firstEvent.setName("Seminário de tecnologia");
            firstEvent.setDescription("Seminário de tecnologia com palestrantes locais");
            firstEvent.setTicketQuantity(10);
            firstEvent.setTicketPrice(10.0F);
            firstEvent.setCreator(user1.orElse(null));
            firstEvent.setSpeakers(Arrays.asList(speaker1.orElse(null), speaker2.orElse(null)));

            SeminarEvent secondEvent = new SeminarEvent();
            secondEvent.setName("Seminário de negócios");
            secondEvent.setDescription("Seminário de negócios com palestrantes locais");
            secondEvent.setTicketQuantity(20);
            secondEvent.setTicketPrice(20.0F);
            secondEvent.setCreator(user1.orElse(null));
            secondEvent.setSpeakers(Arrays.asList(speaker2.orElse(null), speaker3.orElse(null)));

            SeminarEvent thirdEvent = new SeminarEvent();
            thirdEvent.setName("Seminário de marketing");
            thirdEvent.setDescription("Seminário de marketing com palestrantes locais");
            thirdEvent.setTicketQuantity(10);
            thirdEvent.setTicketPrice(10.0F);
            thirdEvent.setCreator(user2.orElse(null));
            thirdEvent.setSpeakers(Arrays.asList(speaker3.orElse(null), speaker4.orElse(null)));

            SeminarEvent fourthEvent = new SeminarEvent();
            fourthEvent.setName("Seminário de vendas");
            fourthEvent.setDescription("Seminário de vendas com palestrantes locais");
            fourthEvent.setTicketQuantity(20);
            fourthEvent.setTicketPrice(20.0F);
            fourthEvent.setCreator(user2.orElse(null));
            fourthEvent.setSpeakers(Arrays.asList(speaker4.orElse(null), speaker5.orElse(null)));

            seminarEventRepository.saveAll(Arrays.asList(firstEvent, secondEvent, thirdEvent, fourthEvent));
        }
    }
}