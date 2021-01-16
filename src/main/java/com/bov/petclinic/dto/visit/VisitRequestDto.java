package com.bov.petclinic.dto.visit;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class VisitRequestDto {
    private long id;
    private Date visitDate;
    private String goalOfVisit;
    private long pet;
}
