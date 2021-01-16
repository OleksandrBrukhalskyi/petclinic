package com.bov.petclinic.dto.veterinarian;

import com.bov.petclinic.dto.specialty.SpecialtyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeterinarianResponseDto {
    private long id;
    private String surname;
    private String firstname;
    private String patronymic;
    private SpecialtyDto specialty;
}
