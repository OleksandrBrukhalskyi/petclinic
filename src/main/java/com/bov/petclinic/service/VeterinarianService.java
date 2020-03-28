package com.bov.petclinic.service;

import com.bov.petclinic.entity.Veterinarian;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VeterinarianService {
    Veterinarian create(Veterinarian veterinarian);
    Veterinarian update(Veterinarian veterinarian);
    Veterinarian getById(Long id);
    void delete(Long id);
    List<Veterinarian> getAll();
}
