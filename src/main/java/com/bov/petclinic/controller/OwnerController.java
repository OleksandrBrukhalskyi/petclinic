package com.bov.petclinic.controller;

import com.bov.petclinic.dto.OwnerDto;
import com.bov.petclinic.forms.OwnerForm;
import com.bov.petclinic.entity.Owner;
import com.bov.petclinic.entity.Pet;
import com.bov.petclinic.repository.OwnerRepository;
import com.bov.petclinic.repository.PetRepository;
import com.bov.petclinic.service.OwnerService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@NoArgsConstructor
@RequestMapping("/api/owner")
@CrossOrigin("*")
public class OwnerController {

    private OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }


    @PostMapping("/add")
    public ResponseEntity<OwnerDto> create(@Valid @RequestBody OwnerDto ownerDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ownerService.create(ownerDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<OwnerDto> update(@Valid @RequestBody OwnerDto ownerDto, @PathVariable("id") Long id){
        Owner owner = ownerService.getById(id);
        owner.setSurname(ownerDto.getSurname());
        owner.setFirstname(ownerDto.getFirstname());
        owner.setPatronymic(ownerDto.getPatronymic());
        owner.setHomeAddress(ownerDto.getHomeAddress());
        owner.setPhoneNumber(ownerDto.getNumber());
        return ResponseEntity.status(HttpStatus.OK)
                .body(ownerService.update(ownerDto, id));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Owner> getById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(ownerService.getById(id));
    }
    @GetMapping
    public List<Owner> getAll(){
        return ownerService.getAll();
    }
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable("id") Long id){
        ownerService.delete(id);
    }

}
