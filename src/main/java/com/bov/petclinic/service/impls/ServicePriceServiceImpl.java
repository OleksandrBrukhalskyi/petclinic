package com.bov.petclinic.service.impls;

import com.bov.petclinic.dto.ServicePriceDto;
import com.bov.petclinic.entity.ServicePrice;
import com.bov.petclinic.exceptions.BadIdException;
import com.bov.petclinic.repository.ServicePriceRepository;
import com.bov.petclinic.service.ServicePriceService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ServicePriceServiceImpl implements ServicePriceService {
    @Autowired
    private ServicePriceRepository servicePriceRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ServicePrice create(ServicePrice servicePrice) {
        return servicePriceRepository.save(servicePrice);
    }

    @Override
    public ServicePrice update(ServicePrice servicePrice) {
        return servicePriceRepository.save(servicePrice);
    }

    @Override
    public ServicePrice getById(long id) {
       return servicePriceRepository.findById(id).get();

    }

    @Override
    public List<ServicePriceDto> getAll() {
        return servicePriceRepository.findAll()
                .stream()
                .map(servicePrice -> modelMapper.map(servicePrice,ServicePriceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(long id) {
        servicePriceRepository.deleteById(id);
    }
}
