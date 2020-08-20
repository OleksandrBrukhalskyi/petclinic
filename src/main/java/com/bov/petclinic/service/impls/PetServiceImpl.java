package com.bov.petclinic.service.impls;

import com.bov.petclinic.dto.PetDtoRequest;
import com.bov.petclinic.dto.PetDtoResponse;
import com.bov.petclinic.entity.Owner;
import com.bov.petclinic.entity.Pet;
import com.bov.petclinic.repository.PetRepository;
import com.bov.petclinic.service.OwnerService;
import com.bov.petclinic.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PetServiceImpl implements PetService {
    private PetRepository petRepository;
    private ModelMapper modelMapper;
    private OwnerService ownerService;

    @Autowired
    public PetServiceImpl(PetRepository petRepository, ModelMapper modelMapper, OwnerService ownerService) {
        this.petRepository = petRepository;
        this.modelMapper = modelMapper;
        this.ownerService = ownerService;
    }


    @Override
    public PetDtoResponse create(PetDtoRequest petDtoRequest) {
        log.info("Pet added");
        Pet toSave = modelMapper.map(petDtoRequest,Pet.class);
        Owner owner = ownerService.getById(petDtoRequest.getOwner());
        toSave.setName(petDtoRequest.getName());
        toSave.setBreed(petDtoRequest.getBreed());
        toSave.setDateOfBirth(petDtoRequest.getDateOfBirth());
        toSave.setOwner(owner);
        try{
            petRepository.save(toSave);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return modelMapper.map(toSave,PetDtoResponse.class);
    }

    @Override
    public Pet update(Pet pet) {
        log.info("Pet updated!");
        return petRepository.save(pet);
    }

    @Override
    public Pet getById(Long id) {
        log.info("Pet found by this id:" + id);
        return petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet not found"));
    }

    @Override
    public void delete(Long id) {
        log.info("Pet deleted");
        this.petRepository.deleteById(id);
    }

    @Override
    public List<Pet> getAll() {
        log.info("List of pets",petRepository.findAll());
        return petRepository.findAll();
    }
}
