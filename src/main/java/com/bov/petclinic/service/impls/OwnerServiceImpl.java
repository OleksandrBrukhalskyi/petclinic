package com.bov.petclinic.service.impls;

import com.bov.petclinic.entity.Owner;
import com.bov.petclinic.repository.OwnerRepository;
import com.bov.petclinic.service.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;
    @Override
    public Owner create(Owner owner) {
        log.info("Owner created");
        return ownerRepository.save(owner);
    }

    @Override
    public Owner update(Owner owner) {
        log.info("Owner updated");
        return ownerRepository.save(owner);
    }

    @Override
    public Owner getById(Long id) {
        log.info("Owner found");
        return ownerRepository.findById(id).orElseThrow(() -> new RuntimeException("Owner did not found"));
    }

    @Override
    public void delete(Long id) {
        log.info("Onwer deleted");
        this.ownerRepository.deleteById(id);
    }

    @Override
    public List<Owner> getAll() {
        log.info("List of Owners");
        return ownerRepository.findAll();
    }
}
