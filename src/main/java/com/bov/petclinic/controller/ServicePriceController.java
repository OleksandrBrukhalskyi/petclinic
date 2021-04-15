package com.bov.petclinic.controller;

import com.bov.petclinic.constant.HttpStatuses;
import com.bov.petclinic.dto.ServicePriceDto;
import com.bov.petclinic.entity.ServicePrice;
import com.bov.petclinic.service.impls.ServicePriceServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/price")
@CrossOrigin(origins = "*")
public class ServicePriceController {
    @Autowired
    private ServicePriceServiceImpl priceService;

    @ApiOperation(value = "Add price")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @PostMapping("/add")
    public ResponseEntity<ServicePrice> add(@Valid @RequestBody ServicePrice servicePrice){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(priceService.create(servicePrice));
    }

    @ApiOperation(value = "Update price")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ServicePrice> updatePrice(@Valid @RequestBody ServicePrice servicePrice, @PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(priceService.update(servicePrice));
    }
    @ApiOperation(value = "Return service price  by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ServicePrice> getPriceServiceById(@PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(priceService.getById(id));
    }
    @ApiOperation(value = "Get all prices")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @GetMapping
    public List<ServicePriceDto> getAllPrices(){
        return priceService.getAll();
    }
    @ApiOperation(value = "Delete  service price  by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @DeleteMapping("/{id}")
    void deleteServicePrice(@PathVariable("id") long id){
        priceService.deleteById(id);
    }
}
