package com.bov.petclinic.dto;

import com.bov.petclinic.entity.Owner;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetDtoRequest {
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private String breed;
    private Long owner;


}
