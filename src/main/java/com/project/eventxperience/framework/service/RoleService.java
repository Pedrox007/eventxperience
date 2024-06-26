package com.project.eventxperience.framework.service;

import com.project.eventxperience.framework.model.Role;
import com.project.eventxperience.framework.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        try {
            return roleRepository.save(role);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao salvar a função no banco de dados", e);
        }
    }

    public List<Role> getRoleByUserName(String username) {
        try {
            return roleRepository.findRoleByUserName(username);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Erro ao buscar o perfil pelo nome do usuário no banco de dados", e);
        }
    }
}

