package com.bov.petclinic.entity;


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
    private long id;
    @Column(name = "visit_date")
    private Date visitDate;
    @Column(name = "goal_of_visit")
    private String goalOfVisit;
    @ManyToOne(fetch = FetchType.LAZY)
    private Pet pet;

}
