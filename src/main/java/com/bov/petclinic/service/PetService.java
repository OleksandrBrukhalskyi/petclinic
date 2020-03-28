package com.bov.petclinic.service;

import com.bov.petclinic.entity.Pet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PetService {
    Pet create(Pet pet);
    Pet update(Pet pet);
    Pet getById(Long id);
    void delete(Long id);
    List<Pet> getAll();
}
