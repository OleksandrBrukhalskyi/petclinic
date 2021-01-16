package com.bov.petclinic.service;

import com.bov.petclinic.dto.visit.VisitRequestDto;
import com.bov.petclinic.dto.visit.VisitResponseDto;
import com.bov.petclinic.entity.Visit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VisitService {
    VisitResponseDto create(VisitRequestDto visit);
    VisitResponseDto update(VisitRequestDto visit);
    Visit getById(long id);
    void delete(long id);
    List<VisitResponseDto> getAll();
}
