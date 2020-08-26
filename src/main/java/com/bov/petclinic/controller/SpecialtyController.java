package com.bov.petclinic.controller;

import com.bov.petclinic.constant.HttpStatuses;
import com.bov.petclinic.entity.Specialty;
import com.bov.petclinic.service.SpecialtyService;
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
@RequestMapping("/api/specialty")
@CrossOrigin("4200")
public class SpecialtyController {
    private final SpecialtyService specialtyService;

    @Autowired
    public SpecialtyController(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }
    @ApiOperation(value = "Save specialty")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @PostMapping("/add")
    public ResponseEntity<Specialty> create(@Valid @RequestBody Specialty specialty){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(specialtyService.create(specialty));
    }
    @ApiOperation(value = "Update specialty")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Specialty> update(@Valid @RequestBody Specialty specialty, @PathVariable("id") Long id){
        Specialty returnedSpecialtyById = specialtyService.getById(id);
        returnedSpecialtyById.setSpecialty(specialty.getSpecialty());
        return ResponseEntity.status(HttpStatus.OK)
                .body(specialtyService.update(specialty,id));
    }
    @ApiOperation(value = "Return specialty by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Specialty> getById(@RequestParam("id") Long id){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(specialtyService.getById(id));
    }
    @ApiOperation(value = "Get all specialties")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @GetMapping
    public List<Specialty> getAll(){
        return specialtyService.getAll();
    }
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable("id") Long id){
        specialtyService.delete(id);
        return id;
    }
}
