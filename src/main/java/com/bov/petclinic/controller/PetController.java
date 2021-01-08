package com.bov.petclinic.controller;

import com.bov.petclinic.constant.HttpStatuses;
import com.bov.petclinic.dto.PetDtoRequest;
import com.bov.petclinic.dto.PetDtoResponse;
import com.bov.petclinic.entity.Pet;
import com.bov.petclinic.service.PetService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pet")
@CrossOrigin("4200")
public class PetController {

    private final PetService petService;


    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;

    }
    @ApiOperation(value = "Save pet")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @PostMapping("/add")
    public ResponseEntity<PetDtoResponse> create(@Valid @RequestBody PetDtoRequest petDtoRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(petService.create(petDtoRequest));

    }
    @ApiOperation(value = "Update pet")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @PutMapping("/{id}")
    public ResponseEntity<PetDtoResponse> update(@Valid @RequestBody PetDtoRequest petDtoRequest, @PathVariable Long id){
        petDtoRequest.setId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(petService.update(petDtoRequest));
    }
    @ApiOperation(value = "Return pet by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Pet> getById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(petService.getById(id));
    }
    @ApiOperation(value = "Get all pets")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @GetMapping
    public List<PetDtoResponse> getAll(){
        return petService.getAll();
    }
    @ApiOperation(value = "Delete pet")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        this.petService.delete(id);
    }
}
