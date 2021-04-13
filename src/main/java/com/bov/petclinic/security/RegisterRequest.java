package com.bov.petclinic.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String surname;
    private String firstname;
    private String patronymic;
    private String email;
    private String login;
    private String password;
}
