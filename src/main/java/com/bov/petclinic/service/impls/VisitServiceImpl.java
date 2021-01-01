package com.bov.petclinic.service.impls;

import com.bov.petclinic.entity.Visit;
import com.bov.petclinic.exceptions.BadIdException;
import com.bov.petclinic.repository.VisitRepository;
import com.bov.petclinic.service.VisitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class VisitServiceImpl implements VisitService {
    private final VisitRepository visitRepository;

    @Autowired
    public VisitServiceImpl(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Visit create(Visit visit) {
        log.info("Using 'create' method for saving visit record into DB " + visit);
        return visitRepository.save(visit);
    }

    @Override
    public Visit update(Visit visit) {
        log.info("Using 'update' method for updating visit record" + visit);
        if(visit != null){
            Visit oldVisit = getById(visit.getId());
            if(oldVisit != null){
                return visitRepository.save(visit);
            }
        }
        throw new NullPointerException("Visit can't be 'null'");

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
