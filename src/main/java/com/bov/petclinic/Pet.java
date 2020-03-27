package com.bov.petclinic;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "pets")
@Data
public class Pet {
    private Long id;
    private String name;
    private Date dateOfBirth;
    private String type;
    private Owner owner;
}
