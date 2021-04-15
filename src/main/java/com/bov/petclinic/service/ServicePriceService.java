package com.bov.petclinic.service;

import com.bov.petclinic.dto.ServicePriceDto;
import com.bov.petclinic.entity.ServicePrice;

import java.util.List;
import java.util.Optional;


public interface ServicePriceService {
    ServicePrice create(ServicePrice servicePrice);
    ServicePrice update(ServicePrice servicePrice);
    ServicePrice getById(long id);
    List<ServicePriceDto> getAll();
    void deleteById(long id);

}
