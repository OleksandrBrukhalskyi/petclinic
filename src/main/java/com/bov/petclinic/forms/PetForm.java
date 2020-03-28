package com.bov.petclinic.forms;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class PetForm {

    private Long id;
    private String name;
    private Date dateOfBirth;
    private String breed;
    private Long owner;
}
