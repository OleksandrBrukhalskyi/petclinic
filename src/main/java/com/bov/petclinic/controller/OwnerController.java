package com.bov.petclinic.controller;

import com.bov.petclinic.constant.HttpStatuses;
import com.bov.petclinic.dto.OwnerDto;
import com.bov.petclinic.entity.Owner;
import com.bov.petclinic.service.OwnerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@NoArgsConstructor
@RequestMapping("/api/owner")
@CrossOrigin("4200")
public class OwnerController {

    private OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @ApiOperation(value = "Save owners")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @PostMapping("/add")
    public ResponseEntity<OwnerDto> create(@Valid @RequestBody OwnerDto ownerDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ownerService.create(ownerDto));
    }
    @ApiOperation(value = "Update owner")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @PutMapping("/{id}")
    public ResponseEntity<OwnerDto> update(@Valid @RequestBody OwnerDto ownerDto, @PathVariable("id") Long id){
        Owner owner = ownerService.getById(id);
        owner.setId(ownerDto.getId());
        owner.setSurname(ownerDto.getSurname());
        owner.setFirstname(ownerDto.getFirstname());
        owner.setPatronymic(ownerDto.getPatronymic());
        owner.setHomeAddress(ownerDto.getHomeAddress());
        owner.setPhoneNumber(ownerDto.getPhoneNumber());
        return ResponseEntity.status(HttpStatus.OK)
                .body(ownerService.update(ownerDto,id));
    }
    @ApiOperation("Get owner by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Owner> getById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(ownerService.getById(id));
    }
    @ApiOperation(value = "Get all owners.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
    })
    @GetMapping
    public List<Owner> getAll(){
        return ownerService.getAll();
    }
    @ApiOperation(value = "Delete owner")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable("id") Long id){
        ownerService.delete(id);
        return id;
    }

}
