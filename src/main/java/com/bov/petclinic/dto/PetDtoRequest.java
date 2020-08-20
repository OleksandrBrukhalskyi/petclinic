package com.bov.petclinic.dto;

import com.bov.petclinic.entity.Owner;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetDtoRequest {
    private Long id;
    private String name;
    private Date dateOfBirth;
    private String breed;
    private Long owner;


}
