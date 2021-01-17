package com.bov.petclinic.security;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AuthRequest {
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
}
