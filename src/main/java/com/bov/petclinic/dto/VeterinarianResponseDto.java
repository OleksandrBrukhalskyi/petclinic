package com.bov.petclinic.dto;

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
    private long specialty;
}
