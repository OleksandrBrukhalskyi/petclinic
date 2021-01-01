package com.bov.petclinic.service;

import com.bov.petclinic.entity.Specialty;
import com.bov.petclinic.entity.Veterinarian;
import com.bov.petclinic.repository.VeterinarianRepository;
import com.bov.petclinic.service.impls.VeterinarianServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@SpringBootTest
@RunWith(MockitoJUnitRunner.Silent.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class VeterinarianServiceImplTests {
    @InjectMocks
    private VeterinarianServiceImpl veterinarianService;
    @Mock
    private VeterinarianRepository veterinarianRepository;

    private Specialty specialty = new Specialty(3L,"Хірург");

    @Test
    public void create(){
        Veterinarian veterinarian = new Veterinarian();
        veterinarian.setId(1L);
        veterinarian.setSurname("Іванов");
        veterinarian.setFirstname("Іван");
        veterinarian.setPatronymic("Іванович");
        veterinarian.setSpecialty(specialty);

        when(veterinarianRepository.save(veterinarian)).thenReturn(veterinarian);
        assertEquals(veterinarian,veterinarianService.create(veterinarian));
    }

}
