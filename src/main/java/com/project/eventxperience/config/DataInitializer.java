package com.project.eventxperience.config;

import com.project.eventxperience.model.Role;
import com.project.eventxperience.model.Sport;
import com.project.eventxperience.model.User;
import com.project.eventxperience.repository.RoleRepository;
import com.project.eventxperience.repository.SportRepository;
import com.project.eventxperience.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final SportRepository sportRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(RoleRepository roleRepository, UserRepository userRepository, SportRepository sportRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.sportRepository = sportRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        roleRepository.deleteAll();
        sportRepository.deleteAll();

        // Criar roles
        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        roleUser.setDescription("Usuário regular que frequenta os eventos");

        Role roleOrganizer = new Role();
        roleOrganizer.setName("ROLE_ORGANIZER");
        roleOrganizer.setDescription("Organizador de eventos");

        roleRepository.saveAll(Arrays.asList(roleUser, roleOrganizer));

        // Criar usuários
        User user1 = new User();
        user1.setUsername("carol");
        user1.setEmail("carol@example.com");
        user1.setCpf("98765432100");
        user1.setPhone("991598681");
        user1.setPassword(passwordEncoder.encode("password1"));
        user1.setRoles(Arrays.asList(roleUser));

        User user2 = new User();
        user2.setUsername("pedro");
        user2.setEmail("pedro@example.com");
        user2.setCpf("12334567899");
        user2.setPhone("991497681");
        user2.setPassword(passwordEncoder.encode("password2"));
        user2.setRoles(Arrays.asList(roleOrganizer));

        User user3 = new User();
        user3.setUsername("bruno");
        user3.setEmail("bruno@example.com");
        user3.setCpf("12334567898");
        user3.setPhone("991497681");
        user3.setPassword(passwordEncoder.encode("password3"));
        user3.setRoles(Arrays.asList(roleOrganizer));

        userRepository.saveAll(Arrays.asList(user1, user2, user3));

        // Criar esportes
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
}
