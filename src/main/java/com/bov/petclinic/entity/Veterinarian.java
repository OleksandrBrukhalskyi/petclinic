package com.bov.petclinic.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "veterinarians")
@Data
public class Veterinarian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "surname")
    private String surname;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "patronymic")
    private String patronymic;
    @OneToOne
    private Specialty specialty;

}
