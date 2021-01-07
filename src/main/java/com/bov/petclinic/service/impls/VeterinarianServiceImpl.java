package com.bov.petclinic.service.impls;

import com.bov.petclinic.entity.Specialty;
import com.bov.petclinic.entity.Veterinarian;
import com.bov.petclinic.exceptions.BadIdException;
import com.bov.petclinic.repository.VeterinarianRepository;
import com.bov.petclinic.service.SpecialtyService;
import com.bov.petclinic.service.VeterinarianService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class VeterinarianServiceImpl implements VeterinarianService {
    private final VeterinarianRepository veterinarianRepository;
    private final SpecialtyService specialtyService;

    @Autowired
    public VeterinarianServiceImpl(VeterinarianRepository veterinarianRepository, SpecialtyService specialtyService) {
        this.veterinarianRepository = veterinarianRepository;
        this.specialtyService = specialtyService;
    }

  

    @Override
    public Veterinarian getById(long id) {
        log.info("Using 'getById' method for retrieving record by id: " + id);
        Optional<Veterinarian> optional = veterinarianRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new BadIdException("Veterinarian with id " + id + " not found");
    }
    @Override
    public void delete(long id) {
        log.info("Using 'delete' method for deleting record from DB by id: " + id);
        Veterinarian veterinarian = getById(id);
        if(veterinarian != null){
            veterinarianRepository.delete(veterinarian);
        } else {
            throw new BadIdException("Veterinarian with id " + id + " not found");
        }
    }

    @Override
    public List<Veterinarian> getAll() {
        log.info("Using 'getAll' method for retrieving all records from DB");
        List<Veterinarian> veterinarians = veterinarianRepository.findAll();
        return veterinarians.isEmpty() ? new ArrayList<>() : veterinarians;
    }
}
