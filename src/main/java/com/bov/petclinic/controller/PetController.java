package com.bov.petclinic.controller;

import com.bov.petclinic.dto.PetDtoRequest;
import com.bov.petclinic.dto.PetDtoResponse;
import com.bov.petclinic.entity.Owner;
import com.bov.petclinic.entity.Pet;
import com.bov.petclinic.forms.PetForm;
import com.bov.petclinic.service.OwnerService;
import com.bov.petclinic.service.PetService;
import com.bov.petclinic.service.impls.OwnerServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pet")
@CrossOrigin("*")
public class PetController {

    private final PetService petService;
    private final OwnerService ownerService;

    @Autowired
    public PetController(PetService petService, OwnerService ownerService) {
        this.petService = petService;
        this.ownerService = ownerService;
    }

    @PostMapping("/add")
    public ResponseEntity<PetDtoResponse> create(@Valid @RequestBody PetDtoRequest petDtoRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(petService.create(petDtoRequest));

    }
    @PutMapping("/{id}")
    public ResponseEntity<PetDtoResponse> update(@Valid @RequestBody PetDtoRequest petDtoRequest, @PathVariable("id") Long id){
        Owner owner = ownerService.getById(petDtoRequest.getOwner());
        Pet pet = petService.getById(id);
        pet.setName(petDtoRequest.getName());
        pet.setBreed(petDtoRequest.getBreed());
        pet.setDateOfBirth(petDtoRequest.getDateOfBirth());
        pet.setOwner(owner);

        return ResponseEntity.status(HttpStatus.OK)
                .body(petService.update(petDtoRequest,id));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Pet> getById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(petService.getById(id));
    }
    @GetMapping
    public List<Pet> getAll(){
        return petService.getAll();
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        this.petService.delete(id);
    }
}
