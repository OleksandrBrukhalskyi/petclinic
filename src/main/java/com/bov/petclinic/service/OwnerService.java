package com.bov.petclinic.service;

import com.bov.petclinic.dto.OwnerDto;
import com.bov.petclinic.entity.Owner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OwnerService {
    OwnerDto create(OwnerDto ownerDto);
    OwnerDto update(OwnerDto ownerDto, long id);
    Owner getById(long id);
    void delete(long id);
    List<Owner> getAll();
    List<OwnerDto> getAllDto();
}
