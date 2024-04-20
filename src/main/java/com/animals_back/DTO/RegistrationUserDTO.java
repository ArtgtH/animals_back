package com.animals_back.DTO;

import lombok.Data;

@Data
public class RegistrationUserDTO {
    private String username;
    private String password;
    private String confirmedPassword;
    private String email;
}