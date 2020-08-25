package com.bov.petclinic;

import com.bov.petclinic.entity.Specialty;
import com.bov.petclinic.repository.SpecialtyRepository;
import com.bov.petclinic.service.impls.SpecialtyServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.Silent.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SpecialtyServiceImplTests {
    @InjectMocks
    private SpecialtyServiceImpl specialtyService;
    @Mock
    private SpecialtyRepository specialtyRepository;

    private List<Specialty> specialtyList = Arrays.asList(new Specialty(1L,"Хірург"),new Specialty(2L,"Анастезіолог"));
    private Specialty specialty = new Specialty(3L,"Хірург");

    @Test
    public void getAll(){
        when(specialtyRepository.findAll()).thenReturn(specialtyList);
        assertEquals(specialtyService.getAll(),specialtyList);
    }
    @Test
    public void create(){
        when(specialtyRepository.save(specialty)).thenReturn(specialty);
        assertEquals(specialty,specialtyService.create(specialty));
    }
    @Test
    public void getById(){
        when(specialtyRepository.findById(specialty.getId())).thenReturn(Optional.of(specialty));
        assertEquals(specialty,specialtyService.getById(3L));
    }

    @Test
    public void delete(){
        doNothing().when(specialtyRepository).deleteById(1L);
        when(specialtyRepository.findById(anyLong()))
                .thenReturn(Optional.of(specialty));
        specialtyService.delete(1L);
        verify(specialtyRepository,times(1)).deleteById(1L);
    }

}
