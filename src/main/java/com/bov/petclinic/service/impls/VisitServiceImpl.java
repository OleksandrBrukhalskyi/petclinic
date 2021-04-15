package com.bov.petclinic.service.impls;

import com.bov.petclinic.dto.visit.VisitRequestDto;
import com.bov.petclinic.dto.visit.VisitResponseDto;
import com.bov.petclinic.entity.Pet;
import com.bov.petclinic.entity.ServicePrice;
import com.bov.petclinic.entity.Visit;
import com.bov.petclinic.exceptions.BadIdException;
import com.bov.petclinic.repository.VisitRepository;
import com.bov.petclinic.service.PetService;
import com.bov.petclinic.service.VisitService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class VisitServiceImpl implements VisitService {
    private final VisitRepository visitRepository;
    private final ModelMapper modelMapper;
    private final PetService petService;
    private final ServicePriceServiceImpl priceService;

    @Autowired
    public VisitServiceImpl(VisitRepository visitRepository, ModelMapper modelMapper, PetService petService, ServicePriceServiceImpl priceService) {
        this.visitRepository = visitRepository;
        this.modelMapper = modelMapper;
        this.petService = petService;
        this.priceService = priceService;
    }

    @Override
    public VisitResponseDto create(VisitRequestDto visitRequestDto) {
        if(visitRequestDto == null){
            throw new NullPointerException("Visit is null");
        }
        Pet pet = petService.getById(visitRequestDto.getPet());
        ServicePrice servicePrice = priceService.getById(visitRequestDto.getPrice());
        Visit toSave = modelMapper.map(visitRequestDto,Visit.class);
        toSave.setVisitDate(visitRequestDto.getVisitDate());
        toSave.setGoalOfVisit(visitRequestDto.getGoalOfVisit());
        toSave.setPrice(servicePrice);
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
        ServicePrice servicePrice = priceService.getById(visitRequestDto.getPrice());
        Visit toUpdate = modelMapper.map(visitRequestDto,Visit.class);
        toUpdate.setGoalOfVisit(visitRequestDto.getGoalOfVisit());
        toUpdate.setVisitDate(visitRequestDto.getVisitDate());
        toUpdate.setPrice(servicePrice);
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
        return visitRepository.findById(id).get();


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
    public List<VisitResponseDto> getAll() {
        log.info("Using 'getAll' method for retrieving all visit records from DB");
        return visitRepository.findAll()
                .stream()
                .map(visit -> modelMapper.map(visit,VisitResponseDto.class))
                .collect(Collectors.toList());
    }
    public Map<String, Long> groupVisitsByCounts(){
        List<Visit> visits =  visitRepository.findAll();
        Map<String,Long> counting = visits.stream()
                .collect(Collectors.groupingBy(Visit::getGoalOfVisit,Collectors.counting()));
        return counting;

    }
}
