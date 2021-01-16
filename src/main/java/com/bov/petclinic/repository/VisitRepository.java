package com.bov.petclinic.repository;

import com.bov.petclinic.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit,Long>{

}