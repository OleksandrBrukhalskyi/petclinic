package com.bov.petclinic;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "pets")
@Data
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "type")
    private String type;
    @ManyToOne
    private Owner owner;
}
