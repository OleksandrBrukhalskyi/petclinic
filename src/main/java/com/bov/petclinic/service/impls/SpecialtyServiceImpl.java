package com.bov.petclinic.service.impls;

import com.bov.petclinic.entity.Specialty;
import com.bov.petclinic.repository.SpecialtyRepository;
import com.bov.petclinic.service.SpecialtyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SpecialtyServiceImpl implements SpecialtyService {
    private final SpecialtyRepository specialtyRepository;


    @Autowired
    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;

    }
    @Override
    public Specialty create(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public Specialty update(Specialty specialty) {
        Specialty oldSpecialty = getById(specialty.getId());
        if(oldSpecialty != null){
            return specialtyRepository.save(specialty);
        }
        throw new NullPointerException("Specialty can't be 'null'");
    }
    @Override
    public Specialty getById(long id) {
        return specialtyRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public void delete(long id) {
        specialtyRepository.deleteById(id);
    }

    @Override
    public List<Specialty> getAll() {
        return specialtyRepository.findAll();
    }
}