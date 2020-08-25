package com.bov.petclinic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetDtoResponse {
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private String breed;
    private OwnerDto owner;
}
