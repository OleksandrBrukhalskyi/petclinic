package com.bov.petclinic.service.impls;

import com.bov.petclinic.dto.PetDtoRequest;
import com.bov.petclinic.dto.PetDtoResponse;
import com.bov.petclinic.entity.Owner;
import com.bov.petclinic.entity.Pet;
import com.bov.petclinic.exceptions.BadIdException;
import com.bov.petclinic.repository.PetRepository;
import com.bov.petclinic.service.OwnerService;
import com.bov.petclinic.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        if(petDtoRequest == null){
            throw new NullPointerException("Pet is null");
        }
        Pet toSave = modelMapper.map(petDtoRequest,Pet.class);
        Owner owner = ownerService.getById(petDtoRequest.getOwner());
        toSave.setName(petDtoRequest.getName());
        toSave.setBreed(petDtoRequest.getBreed());
        toSave.setDateOfBirth(petDtoRequest.getDateOfBirth());
        toSave.setOwner(owner);
        try{
            petRepository.save(toSave);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        log.info("Pet added");
        return modelMapper.map(toSave,PetDtoResponse.class);
    }

    @Override
    public PetDtoResponse update(PetDtoRequest petDtoRequest) {
        if(petDtoRequest == null){
            throw new NullPointerException("Pet is null");
        }
        Pet toUpdate = modelMapper.map(petDtoRequest,Pet.class);
        Owner owner = ownerService.getById(petDtoRequest.getOwner());
        toUpdate.setName(petDtoRequest.getName());
        toUpdate.setBreed(petDtoRequest.getBreed());
        toUpdate.setDateOfBirth(petDtoRequest.getDateOfBirth());
        toUpdate.setOwner(owner);
        try{
            petRepository.save(toUpdate);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        log.info("Pet updated!");
        return modelMapper.map(toUpdate,PetDtoResponse.class);

    }

    @Override
    public Pet getById(long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if(optionalPet.isPresent()){
            return optionalPet.get();
        }
        log.info("Pet found by this id:" + id);
        throw new BadIdException("Pet not found by this id: " + id);
    }

    @Override
    public void delete(long id) {
        Pet pet = getById(id);
        if(pet != null){
            petRepository.delete(pet);
            log.info("Pet deleted");
        } else {
            throw new BadIdException("Pet not found by this id: " + id);
        }

    }

    @Override
    public List<PetDtoResponse> getAll() {
      return petRepository.findAll()
              .stream()
              .map(pet -> modelMapper.map(pet,PetDtoResponse.class))
              .collect(Collectors.toList());
    }
}
