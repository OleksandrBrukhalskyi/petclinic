package com.bov.petclinic.entity;

import com.bov.petclinic.entity.Pet;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "owners")
@Data
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "surname")
    private String surname;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "homeAddress")
    private String homeAddress;
    @Column(name = "phone_number")
    private String phoneNumber;

}
