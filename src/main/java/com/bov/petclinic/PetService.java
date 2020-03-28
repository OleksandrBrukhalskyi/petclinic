package com.bov.petclinic;

import com.bov.petclinic.entity.Pet;

import java.util.List;

public interface PetService {
    Pet create(Pet pet);
    Pet update(Pet pet);
    Pet getById(Long id);
    void delete(Long id);
    List<Pet> getAll();
}
