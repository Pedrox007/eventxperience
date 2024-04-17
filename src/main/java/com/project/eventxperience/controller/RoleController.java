package com.project.eventxperience.controller;

import com.project.eventxperience.model.Role;
import com.project.eventxperience.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;



    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<Role> getRoles(@RequestParam String username) {
        return roleService.getRoleByUserName(username);

    }

    @PostMapping("/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        Role savedRole = roleService.saveRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRole);
    }
}
