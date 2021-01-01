package com.bov.petclinic.service;

import com.bov.petclinic.dto.OwnerDto;
import com.bov.petclinic.entity.Owner;
import com.bov.petclinic.repository.OwnerRepository;
import com.bov.petclinic.service.impls.OwnerServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
@SpringBootTest
@RunWith(MockitoJUnitRunner.Silent.class)
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class OwnerServiceImplTests {
    @InjectMocks
    private OwnerServiceImpl ownerService;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private OwnerRepository ownerRepository;


    private OwnerDto ownerDto = new OwnerDto(1L,"Брухальський","Олександр","Валентинович","проспект Незалежності,110","0993468123");
    private Owner owner = new Owner(1L,"Брухальський","Олександр","Валентинович","проспект Незалежності,110","0993468123",Collections.emptyList());


    @Test
    public void getAllOwners(){
        List<Owner> owners = Collections.singletonList(owner);
        when(ownerRepository.findAll()).thenReturn(owners);
        assertEquals(owners,ownerService.getAll());
    }
    @Test
    public void getAllOwnersDto(){

        List<OwnerDto> dtoList = Collections.singletonList(ownerDto);
        List<Owner> ownerList = Collections.singletonList(owner);
        doReturn(ownerList).when(ownerRepository).findAll();
        doReturn(dtoList.get(0)).when(modelMapper).map(owner,OwnerDto.class);
        assertEquals(dtoList,ownerService.getAllDto());
    }
    @Test
    public void getOwnerById(){
        when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner));
        assertEquals(owner,ownerService.getById(1L));
    }
    @Test
    public void save(){
        when(modelMapper.map(ownerDto, Owner.class)).thenReturn(owner);
        when(modelMapper.map(owner, OwnerDto.class)).thenReturn(ownerDto);
        when(ownerRepository.save(owner)).thenReturn(owner);
        assertEquals(ownerDto,ownerService.create(ownerDto));
    }
    @Test
    public void delete(){
        doNothing().when(ownerRepository).deleteById(1L);
        when(ownerRepository.findById(anyLong()))
                .thenReturn(Optional.of(owner));
        ownerService.delete(1L);
        verify(ownerRepository,times(1)).deleteById(1L);
    }
    @Test
    public void update(){
        OwnerDto given = new OwnerDto(1L,"Брухальський","Олександр","Валентинович","проспект Незалежності,110","0993468123");
        OwnerDto updated = new OwnerDto(1L,"Іванов","Олександр","Валентинович","проспект Незалежності,110","0993468123");
        //when(ownerRepository.findById(given.getId())).thenReturn(Optional.of(given));
        when(ownerRepository.save(modelMapper.map(updated,Owner.class))).thenReturn(owner);
        OwnerDto actual = ownerService.update(updated,1);
        assertEquals(updated,actual);
    }


}
