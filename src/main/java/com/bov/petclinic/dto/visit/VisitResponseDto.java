package com.bov.petclinic.dto.visit;

import com.bov.petclinic.dto.ServicePriceDto;
import com.bov.petclinic.dto.pet.PetDtoResponse;
import com.bov.petclinic.entity.ServicePrice;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class VisitResponseDto {
    private long id;
    private LocalDateTime visitDate;
    private String goalOfVisit;
    private ServicePriceDto price;
    private PetDtoResponse pet;
}
