package com.bov.petclinic.service;

import com.bov.petclinic.dto.pet.PetDtoRequest;
import com.bov.petclinic.dto.pet.PetDtoResponse;
import com.bov.petclinic.entity.Pet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PetService {
    PetDtoResponse create(PetDtoRequest petDtoRequest);
    PetDtoResponse update(PetDtoRequest petDtoRequest);
    Pet getById(long id);
    void delete(long id);
    List<PetDtoResponse> getAll();
}
