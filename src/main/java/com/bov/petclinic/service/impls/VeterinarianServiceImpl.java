package com.bov.petclinic.service.impls;

import com.bov.petclinic.dto.VeterinarianRequestDto;
import com.bov.petclinic.dto.VeterinarianResponseDto;
import com.bov.petclinic.entity.Specialty;
import com.bov.petclinic.entity.Veterinarian;
import com.bov.petclinic.exceptions.BadIdException;
import com.bov.petclinic.repository.VeterinarianRepository;
import com.bov.petclinic.service.SpecialtyService;
import com.bov.petclinic.service.VeterinarianService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    @Autowired
    public VeterinarianServiceImpl(VeterinarianRepository veterinarianRepository, SpecialtyService specialtyService, ModelMapper modelMapper) {
        this.veterinarianRepository = veterinarianRepository;
        this.specialtyService = specialtyService;
        this.modelMapper = modelMapper;
    }
    @Override
    public VeterinarianResponseDto create(VeterinarianRequestDto veterinarian) {
        if(veterinarian == null){
            throw new NullPointerException("Veterinarian is null");
        }
        Specialty specialty = specialtyService.getById(veterinarian.getSpecialty());
        Veterinarian toSave = modelMapper.map(veterinarian,Veterinarian.class);
        toSave.setSurname(veterinarian.getSurname());
        toSave.setFirstname(veterinarian.getFirstname());
        toSave.setPatronymic(veterinarian.getPatronymic());
        toSave.setSpecialty(specialty);
        try{
            veterinarianRepository.save(toSave);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return modelMapper.map(toSave, VeterinarianResponseDto.class);
    }

    @Override
    public VeterinarianResponseDto update(VeterinarianRequestDto veterinarian) {
        if(veterinarian == null){
            throw new NullPointerException("Veterinarian is null");
        }
        Veterinarian toUpdate = modelMapper.map(veterinarian,Veterinarian.class);
        Specialty specialty = specialtyService.getById(veterinarian.getSpecialty());
        toUpdate.setSurname(veterinarian.getSurname());
        toUpdate.setFirstname(veterinarian.getFirstname());
        toUpdate.setPatronymic(veterinarian.getPatronymic());
        toUpdate.setSpecialty(specialty);
        try{
            veterinarianRepository.save(toUpdate);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return modelMapper.map(toUpdate,VeterinarianResponseDto.class);
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
