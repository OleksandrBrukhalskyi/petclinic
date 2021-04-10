package com.bov.petclinic.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "surname")
    @NotEmpty(message = "Surname is required")
    private String surname;
    @Column(name = "firstname")
    @NotEmpty(message = "Firstname is required")
    private String firstname;
    @Column(name = "patronymic")
    private String patronymic;
    @Email
    @Column(name = "email")
    @NotEmpty(message = "Email is required")
    private String email;
    @Column(name = "password")
    @Min(8)
    @Max(30)
    private String password;
    private Instant created;
    private boolean isEnabled;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
