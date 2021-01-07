package com.bov.petclinic.service;

import com.bov.petclinic.dto.VeterinarianRequestDto;
import com.bov.petclinic.dto.VeterinarianResponseDto;
import com.bov.petclinic.entity.Veterinarian;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VeterinarianService {
    VeterinarianResponseDto create(VeterinarianRequestDto veterinarian);
    VeterinarianResponseDto update(VeterinarianRequestDto veterinarian);
    Veterinarian getById(long id);
    void delete(long id);
    List<Veterinarian> getAll();
}
