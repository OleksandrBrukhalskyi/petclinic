package com.bov.petclinic.controller;

import com.bov.petclinic.constant.HttpStatuses;
import com.bov.petclinic.dto.veterinarian.VeterinarianRequestDto;
import com.bov.petclinic.dto.veterinarian.VeterinarianResponseDto;
import com.bov.petclinic.entity.Veterinarian;
import com.bov.petclinic.service.VeterinarianService;
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
@RequestMapping("/api/veterinarian")
@CrossOrigin(origins = "*")
public class VeterinarianController {
    private final VeterinarianService veterinarianService;


    @Autowired
    public VeterinarianController(VeterinarianService veterinarianService) {
        this.veterinarianService = veterinarianService;
    }

    @ApiOperation(value = "Save veterinarian")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @PostMapping("/add")
    public ResponseEntity<VeterinarianResponseDto> create(@Valid @RequestBody VeterinarianRequestDto veterinarian){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(veterinarianService.create(veterinarian));
    }
    @ApiOperation(value = "Update specialty")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @PutMapping("/{id}")
    public ResponseEntity<VeterinarianResponseDto> update(@Valid @RequestBody VeterinarianRequestDto veterinarianDto, @PathVariable("id") long id){
        veterinarianDto.setId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(veterinarianService.update(veterinarianDto));
    }
    @ApiOperation(value = "Retrieve veterinarian by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Veterinarian> retrieveById(@PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(veterinarianService.getById(id));
    }
    @ApiOperation(value = "Get all veterinarians")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @GetMapping
    public List<Veterinarian> getAll(){
        return veterinarianService.getAll();
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        veterinarianService.delete(id);
    }
}
