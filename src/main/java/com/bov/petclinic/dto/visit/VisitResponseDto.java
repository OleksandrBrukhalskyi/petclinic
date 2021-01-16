package com.bov.petclinic.dto.visit;

import com.bov.petclinic.dto.pet.PetDtoResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class VisitResponseDto {
    private long id;
    private Date visitDate;
    private String goalOfVisit;
    private PetDtoResponse pet;
}
