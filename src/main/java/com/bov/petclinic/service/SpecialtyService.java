package com.bov.petclinic.service;

import com.bov.petclinic.entity.Specialty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SpecialtyService {
    Specialty create(Specialty specialty);
    Specialty update(Specialty specialty, long id);
    Specialty getById(long id);
    void delete(long id);
    List<Specialty> getAll();
}
