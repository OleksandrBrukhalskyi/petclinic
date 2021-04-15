package com.bov.petclinic.repository;

import com.bov.petclinic.entity.ServicePrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicePriceRepository extends JpaRepository<ServicePrice,Long> {
}
