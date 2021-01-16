package com.bov.petclinic.dto.owner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OwnerDto {
    private Long id;
    private String surname;
    private String firstname;
    private String patronymic;
    private String homeAddress;
    private String phoneNumber;
}
