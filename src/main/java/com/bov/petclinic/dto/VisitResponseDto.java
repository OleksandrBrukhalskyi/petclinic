package com.bov.petclinic.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class VisitResponseDto {
    private long id;
    private Date visitDate;
    private String reasonOfVisit;
    private PetDtoResponse pet;
}
