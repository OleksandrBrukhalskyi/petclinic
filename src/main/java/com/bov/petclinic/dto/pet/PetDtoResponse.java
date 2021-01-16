package com.bov.petclinic.dto.pet;

import com.bov.petclinic.dto.owner.OwnerDto;
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
