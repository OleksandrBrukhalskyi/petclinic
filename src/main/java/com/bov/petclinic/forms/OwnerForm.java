package com.bov.petclinic;

import com.bov.petclinic.entity.Pet;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
public class OwnerForm {
    private Long id;
    private String surname;
    private String firstname;
    private String patronymic;
    private String address;
    private String phoneNumber;
    private Long pet;
}
