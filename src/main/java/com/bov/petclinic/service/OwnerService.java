package com.bov.petclinic.service;

import com.bov.petclinic.dto.owner.OwnerDto;
import com.bov.petclinic.entity.Owner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OwnerService {
    OwnerDto create(Owner owner);
    OwnerDto update(Owner owner);
    Owner getById(long id);
    void delete(long id);
    List<Owner> getAll();
    List<OwnerDto> getAllDto();
}
