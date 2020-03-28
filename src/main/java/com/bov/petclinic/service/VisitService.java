package com.bov.petclinic.service;

import com.bov.petclinic.entity.Visit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VisitService {
    Visit create(Visit visit);
    Visit update(Visit visit);
    Visit getById(Long id);
    void delete(Long id);
    List<Visit> getAll();
}
