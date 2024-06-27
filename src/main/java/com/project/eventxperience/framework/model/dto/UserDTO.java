package com.project.eventxperience.framework.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private String username;
    private String email;
    private String password;
    private String cpf;
    private String phone;
    private List<String> roles;
}
