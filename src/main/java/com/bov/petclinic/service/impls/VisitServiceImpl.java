package com.bov.petclinic.service.impls;

import com.bov.petclinic.dto.VisitRequestDto;
import com.bov.petclinic.dto.VisitResponseDto;
import com.bov.petclinic.entity.Pet;
import com.bov.petclinic.entity.Visit;
import com.bov.petclinic.exceptions.BadIdException;
import com.bov.petclinic.repository.VisitRepository;
import com.bov.petclinic.service.PetService;
import com.bov.petclinic.service.VisitService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class VisitServiceImpl implements VisitService {
    private final VisitRepository visitRepository;
    private final ModelMapper modelMapper;
    private final PetService petService;

    @Autowired
    public VisitServiceImpl(VisitRepository visitRepository, ModelMapper modelMapper, PetService petService) {
        this.visitRepository = visitRepository;
        this.modelMapper = modelMapper;
        this.petService = petService;
    }

    @Override
    public VisitResponseDto create(VisitRequestDto visitRequestDto) {
        if(visitRequestDto == null){
            throw new NullPointerException("Visit is null");
        }
        Pet pet = petService.getById(visitRequestDto.getPet());
        Visit toSave = modelMapper.map(visitRequestDto,Visit.class);
        toSave.setVisitDate(visitRequestDto.getVisitDate());
        toSave.setGoalOfVisit(visitRequestDto.getReasonOfVisit());
        toSave.setPet(pet);
        try{
            visitRepository.save(toSave);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        log.info("Using 'create' method for saving visit record into DB: " + toSave);
        return modelMapper.map(toSave,VisitResponseDto.class);

    }

    @Override
    public VisitResponseDto update(VisitRequestDto visitRequestDto) {
        if(visitRequestDto == null){
            throw new NullPointerException("Visit is null");
        }
        Pet pet = petService.getById(visitRequestDto.getPet());
        Visit toUpdate = modelMapper.map(visitRequestDto,Visit.class);
        toUpdate.setGoalOfVisit(visitRequestDto.getReasonOfVisit());
        toUpdate.setVisitDate(visitRequestDto.getVisitDate());
        toUpdate.setPet(pet);
        try{
            visitRepository.save(toUpdate);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        log.info("Using 'update' method for updating visit record" + toUpdate);
        return modelMapper.map(toUpdate,VisitResponseDto.class);

    }

    @Override
    public Visit getById(long id) {
        log.info("Using 'getById' method for retrieving record from DB");
        Optional<Visit> visit = visitRepository.findById(id);
        if(visit.isPresent()){
            return visit.get();
        }
        throw new BadIdException("Visit with id " + id + " not found");

    }

    @Override
    public void delete(long id) {
        log.info("Using 'delete' method for deleting record from DB");
        Visit visit = getById(id);
        if(visit != null){
            visitRepository.delete(visit);
        } else {
            throw new BadIdException("Visit with id " + id + " not found");
        }
    }

    @Override
    public List<Visit> getAll() {
        log.info("Using 'getAll' method for retrieving all visit records from DB");
        List<Visit> visits = visitRepository.findAll();
        return visits.isEmpty() ? new ArrayList<>() : visits;
    }
}
