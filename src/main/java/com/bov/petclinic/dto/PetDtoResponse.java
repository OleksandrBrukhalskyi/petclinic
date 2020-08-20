package com.bov.petclinic.dto;

import com.bov.petclinic.entity.Owner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetDtoResponse {
    private Long id;
    private String name;
    private Date dateOfBirth;
    private String breed;
    private Owner owner;
}
