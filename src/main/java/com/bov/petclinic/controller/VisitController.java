package com.bov.petclinic.controller;

import com.bov.petclinic.constant.HttpStatuses;
import com.bov.petclinic.dto.visit.VisitRequestDto;
import com.bov.petclinic.dto.visit.VisitResponseDto;
import com.bov.petclinic.entity.Visit;
import com.bov.petclinic.service.VisitService;
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
@RequestMapping("/api/visit")
public class VisitController {
    private final VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }
    @ApiOperation(value = "Save visit")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @PostMapping("/add")
    public ResponseEntity<VisitResponseDto> create(@Valid @RequestBody VisitRequestDto visitRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(visitService.create(visitRequestDto));
    }
    @ApiOperation(value = "Update visit")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @PutMapping("/{id}")
    public ResponseEntity<VisitResponseDto> update(@Valid @RequestBody VisitRequestDto visitRequestDto, @PathVariable("id") long id){
        visitRequestDto.setId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(visitService.update(visitRequestDto));
    }
    @ApiOperation(value = "Retrieve visit by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Visit> retrieveById(@PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(visitService.getById(id));
    } @ApiOperation(value = "Get all visits")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @GetMapping
    public List<VisitResponseDto> getAll(){
        return visitService.getAll();
    }
    @ApiOperation(value = "Delete visit by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        visitService.delete(id);
    }

}
