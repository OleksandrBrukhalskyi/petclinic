package com.bov.petclinic.controller;

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
@AllArgsConstructor
@RequestMapping("/api/pet")
@CrossOrigin("*")
public class PetController {

    @Autowired
    private PetService petService;
    @Autowired
    private OwnerService ownerService;

    @PostMapping("/add")
    public ResponseEntity<Pet> create(@Valid @RequestBody PetForm petForm){
        Owner owner = ownerService.getById(petForm.getOwner());
        Pet pet = new Pet();
        pet.setName(petForm.getName());
        pet.setBreed(petForm.getBreed());
        pet.setDateOfBirth(petForm.getDateOfBirth());
        pet.setOwner(owner);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(petService.create(pet));

    }
    @PutMapping("/{id}")
    public ResponseEntity<Pet> update(@Valid @RequestBody PetForm petForm, @PathVariable("id") Long id){
        Owner owner = ownerService.getById(petForm.getOwner());
        Pet pet = petService.getById(id);
        pet.setName(petForm.getName());
        pet.setBreed(petForm.getBreed());
        pet.setDateOfBirth(petForm.getDateOfBirth());
        pet.setOwner(owner);

        return ResponseEntity.status(HttpStatus.OK)
                .body(petService.update(pet));
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
