package com.bov.petclinic.dto;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetDto {
    private Long id;
    private String name;
    private Date dateOfBirth;
    private String breed;


}
