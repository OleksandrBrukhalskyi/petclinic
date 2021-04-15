package com.bov.petclinic.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class ServicePriceDto {
    private long id;
    private BigDecimal price;
}
