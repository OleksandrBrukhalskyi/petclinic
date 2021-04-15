package com.bov.petclinic.dto.visit;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
public class VisitRequestDto {
    private long id;
    private LocalDateTime visitDate;
    private String goalOfVisit;
    private long price;
    private long pet;
}
