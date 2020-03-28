package com.bov.petclinic.forms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeterinarianForm {

    private Long id;
    private String surname;
    private String firstname;
    private String patronymic;
    private Long specialty;
}
