package com.bov.petclinic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "veterinarians")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Veterinarian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "surname")
    private String surname;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "patronymic")
    private String patronymic;
    @OneToOne
    private Specialty specialty;

}
