package com.bov.petclinic.service.impls;

import com.bov.petclinic.entity.Owner;
import com.bov.petclinic.repository.OwnerRepository;
import com.bov.petclinic.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;
    @Override
    public Owner create(Owner owner) {
        return null;
    }

    @Override
    public Owner update(Owner owner) {
        return null;
    }

    @Override
    public Owner getById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Owner> getAll() {
        return ownerRepository.findAll();
    }
}
