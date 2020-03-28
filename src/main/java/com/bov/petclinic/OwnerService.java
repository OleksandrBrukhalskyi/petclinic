package com.bov.petclinic;

import com.bov.petclinic.entity.Owner;

import java.util.List;

public interface OwnerService {
    Owner create(Owner owner);
    Owner update(Owner owner);
    Owner getById(Long id);
    void delete(Long id);
    List<Owner> getAll();
}
