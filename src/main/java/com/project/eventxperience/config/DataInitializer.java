package com.project.eventxperience.config;

import com.project.eventxperience.model.Role;
import com.project.eventxperience.model.Sport;
import com.project.eventxperience.model.SportEvent;
import com.project.eventxperience.model.User;
import com.project.eventxperience.repository.RoleRepository;
import com.project.eventxperience.repository.SportEventRepository;
import com.project.eventxperience.repository.SportRepository;
import com.project.eventxperience.repository.UserRepository;
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
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(RoleRepository roleRepository, UserRepository userRepository, SportRepository sportRepository, SportEventRepository sportEventRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.sportRepository = sportRepository;
        this.sportEventRepository = sportEventRepository;
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
            user1.setCpf("98765432100");
            user1.setPhone("991598681");
            user1.setPassword(passwordEncoder.encode("password1"));
            user1.setRoles(Collections.singletonList(roleUser.orElse(null)));

            User user2 = new User();
            user2.setUsername("pedro");
            user2.setEmail("pedro@example.com");
            user2.setCpf("12334567899");
            user2.setPhone("991497681");
            user2.setPassword(passwordEncoder.encode("password2"));
            user2.setRoles(Collections.singletonList(roleOrganizer.orElse(null)));

            User user3 = new User();
            user3.setUsername("bruno");
            user3.setEmail("bruno@example.com");
            user3.setCpf("12334567898");
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
    }
}
