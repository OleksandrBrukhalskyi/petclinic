package com.bov.petclinic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDto {
    private Long id;
    private String surname;
    private String firstname;
    private String patronymic;
    private String homeAddress;
    private String phoneNumber;
}
