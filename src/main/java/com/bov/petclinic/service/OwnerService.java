package com.bov.petclinic.service;

import com.bov.petclinic.entity.Owner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OwnerService {
    Owner create(Owner owner);
    Owner update(Owner owner);
    Owner getById(Long id);
    void delete(Long id);
    List<Owner> getAll();
}
