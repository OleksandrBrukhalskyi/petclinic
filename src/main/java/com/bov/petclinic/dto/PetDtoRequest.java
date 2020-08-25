package com.bov.petclinic.dto;

import lombok.*;
import java.time.LocalDate;


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
