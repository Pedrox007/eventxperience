package com.project.eventxperience.framework.controller;

import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.model.dto.UserDTO;
import com.project.eventxperience.framework.response.LoginResponse;
import com.project.eventxperience.framework.service.AuthenticationService;
import com.project.eventxperience.framework.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody UserDTO userDTO) {
        User registeredUser = authenticationService.signup(userDTO);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody User user) {
        User authenticatedUser = authenticationService.authenticate(user);
        HashMap<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("userId", authenticatedUser.getId());
        extraClaims.put("email", authenticatedUser.getEmail());
        extraClaims.put("roles", authenticatedUser.getAuthorities());
        String jwtToken = jwtService.generateToken(extraClaims, authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
