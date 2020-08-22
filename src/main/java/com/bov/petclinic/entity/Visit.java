package com.bov.petclinic.entity;

import com.bov.petclinic.entity.Owner;
import com.bov.petclinic.entity.Pet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "visits")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "visit_date")
    private Date visitDate;
    @Column(name = "goal_of_visit")
    private String goalOfVisit;
    @ManyToOne(fetch = FetchType.LAZY)
    private Pet pet;
    @ManyToOne(fetch = FetchType.LAZY)
    private Owner owner;
}
