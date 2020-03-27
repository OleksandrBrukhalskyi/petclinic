package com.bov.petclinic;

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
