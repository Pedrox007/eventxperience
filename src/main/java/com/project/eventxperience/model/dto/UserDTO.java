package com.project.eventxperience.model.dto;

import com.project.eventxperience.model.Role;

import java.util.List;

public class UserDTO {
    private String username;
    private String email;
    private String password;
    private String cpf;
    private String phone;
    private List<String> roles;

    public UserDTO(String username, String email, String password, String cpf, String phone, List<String> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.phone = phone;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
