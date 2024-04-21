package com.project.eventxperience.service;

import com.project.eventxperience.model.Role;
import com.project.eventxperience.model.User;
import com.project.eventxperience.model.dto.UserDTO;
import com.project.eventxperience.repository.RoleRepository;
import com.project.eventxperience.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

        public User signup(UserDTO userDTO) {
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setCpf(userDTO.getCpf());
            user.setEmail(userDTO.getEmail());
            user.setPhone(userDTO.getPhone());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            if (!userDTO.getRoles().isEmpty()) {
                List<String> rolesIds = userDTO.getRoles();
                List<Role> roles = new ArrayList<>();
                try {
                    rolesIds.forEach(name -> {
                        Optional<Role> role = roleRepository.findByName(name);
                        role.ifPresent(roles::add);
                    });
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                user.setRoles(roles);
            }

            return userRepository.save(user);
        }

    public User authenticate(User user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );

        return userRepository.findByUsername(user.getUsername())
                .orElseThrow();
    }
}
