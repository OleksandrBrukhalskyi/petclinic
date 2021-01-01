package com.bov.petclinic.service;

import com.bov.petclinic.repository.VeterinarianRepository;
import com.bov.petclinic.service.impls.VeterinarianServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(MockitoJUnitRunner.Silent.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class VeterinarianServiceImplTests {
    @InjectMocks
    private VeterinarianServiceImpl veterinarianService;
    @Mock
    private VeterinarianRepository veterinarianRepository;


    @Test
    public void getAll(){

    }
}
