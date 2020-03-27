package com.bov.petclinic.entity;

import com.bov.petclinic.entity.Owner;
import com.bov.petclinic.entity.Pet;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "visits")
@Data
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "visit_date")
    private Date visitDate;
    @Column(name = "goal_of_visit")
    private String goalOfVisit;
    @ManyToOne
    private Pet pet;
    @ManyToOne
    private Owner owner;
}
