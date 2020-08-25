package com.bov.petclinic.service.impls;

import com.bov.petclinic.entity.Specialty;
import com.bov.petclinic.repository.SpecialtyRepository;
import com.bov.petclinic.service.SpecialtyService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
    public Specialty update(Specialty specialty, Long id) {
        Specialty sp = specialtyRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        sp.setSpecialty(specialty.getSpecialty());
        return specialtyRepository.save(sp);
    }

    @Override
    public Specialty getById(Long id) {
        return specialtyRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public void delete(Long id) {
        specialtyRepository.deleteById(id);
    }

    @Override
    public List<Specialty> getAll() {
        return specialtyRepository.findAll();
    }
}
