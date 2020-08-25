package com.bov.petclinic.service;

import com.bov.petclinic.entity.Specialty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SpecialtyService {
    Specialty create(Specialty specialty);
    Specialty update(Specialty specialty, Long id);
    Specialty getById(Long id);
    void delete(Long id);
    List<Specialty> getAll();
}
