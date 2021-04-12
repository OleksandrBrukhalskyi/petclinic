package com.bov.petclinic.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private long id;
    private String surname;
    private String firstname;
    private String patronymic;
    private String email;
    private String login;
    private String password;
    //private String role;
}
