package com.bov.petclinic.service.impls;

import com.bov.petclinic.dto.OwnerDto;
import com.bov.petclinic.entity.Owner;
import com.bov.petclinic.repository.OwnerRepository;
import com.bov.petclinic.service.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OwnerServiceImpl implements OwnerService {

    private OwnerRepository ownerRepository;
    private ModelMapper modelMapper;
    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository, ModelMapper modelMapper) {
        this.ownerRepository = ownerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OwnerDto create(OwnerDto ownerDto) {
        log.info("Owner created");
        Owner owner = modelMapper.map(ownerDto, Owner.class);
        owner.setSurname(ownerDto.getSurname());
        owner.setFirstname(ownerDto.getFirstname());
        owner.setPatronymic(ownerDto.getPatronymic());
        owner.setHomeAddress(ownerDto.getHomeAddress());
        owner.setPhoneNumber(ownerDto.getNumber());
        return modelMapper.map(ownerRepository.save(owner), OwnerDto.class);
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
