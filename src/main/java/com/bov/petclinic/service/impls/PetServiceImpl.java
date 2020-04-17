package com.bov.petclinic.service.impls;

import com.bov.petclinic.entity.Pet;
import com.bov.petclinic.repository.PetRepository;
import com.bov.petclinic.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PetServiceImpl implements PetService {
    @Autowired
    private PetRepository petRepository;

    @Override
    public Pet create(Pet pet) {
        log.info("Pet added",pet);
        return petRepository.save(pet);
    }

    @Override
    public Pet update(Pet pet) {
        log.info("Pet updated!");
        return petRepository.save(pet);
    }

    @Override
    public Pet getById(Long id) {
        log.info("Pet found by this id:" + id);
        return petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet not found"));
    }

    @Override
    public void delete(Long id) {
        log.info("Pet deleted");
        this.petRepository.deleteById(id);
    }

    @Override
    public List<Pet> getAll() {
        log.info("List of pets",petRepository.findAll());
        return petRepository.findAll();
    }
}
