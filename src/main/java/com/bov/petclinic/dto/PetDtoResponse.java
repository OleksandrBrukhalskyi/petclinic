package com.bov.petclinic.dto;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
public class PetDtoResponse {
    private long id;
    private String name;
    private LocalDate dateOfBirth;
    private String breed;
    private OwnerDto owner;
}
