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
    public Veterinarian create(Veterinarian veterinarian) {
        Specialty specialty = specialtyService.getById(veterinarian.getSpecialty().getId());
        Veterinarian toSave = new Veterinarian();
        toSave.setSurname(veterinarian.getSurname());
        toSave.setFirstname(veterinarian.getFirstname());
        toSave.setPatronymic(veterinarian.getPatronymic());
        toSave.setSpecialty(specialty);
        return veterinarianRepository.save(toSave);
    }

    @Override
    public Veterinarian update(Veterinarian veterinarian) {
        if(veterinarian != null){
            Veterinarian oldVet = getById(veterinarian.getId());
            if(oldVet != null){
                return veterinarianRepository.save(veterinarian);
            }
        }
        throw  new NullPointerException("Veterinarian can't be `null`");
    }

    @Override
    public Veterinarian getById(long id) {
        Optional<Veterinarian> optional = veterinarianRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new BadIdException("Veterinarian with id " + id + " not found");
    }
    @Override
    public void delete(long id) {
        Veterinarian veterinarian = getById(id);
        if(veterinarian != null){
            veterinarianRepository.delete(veterinarian);
        } else {
            throw new BadIdException("Veterinarian with id " + id + " not found");
        }
    }

    @Override
    public List<Veterinarian> getAll() {
        List<Veterinarian> veterinarians = veterinarianRepository.findAll();
        return veterinarians.isEmpty() ? new ArrayList<>() : veterinarians;
    }
}
