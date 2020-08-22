package com.bov.petclinic.dto;

import com.bov.petclinic.entity.Owner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
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
