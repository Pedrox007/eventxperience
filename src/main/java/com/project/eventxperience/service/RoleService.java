package com.project.eventxperience.service;

import com.project.eventxperience.model.Role;
import com.project.eventxperience.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public List<Role> getRoleByUserName(String username) {
        return roleRepository.findRoleByUserName(username);
    }
}

