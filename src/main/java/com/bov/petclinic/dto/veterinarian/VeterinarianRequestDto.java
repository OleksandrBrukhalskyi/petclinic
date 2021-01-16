package com.bov.petclinic.dto.veterinarian;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeterinarianRequestDto {
    private long id;
    private String surname;
    private String firstname;
    private String patronymic;
    private long specialty;
}
