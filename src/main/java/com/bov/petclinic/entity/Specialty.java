package com.bov.petclinic.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "specialties")
@Data
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "specialty")
    private String specialty;
}
