package com.app.clubmatrix.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class DependentRegistrationDTO {

    private String name;
    private String memberUsername;
    private String relationship;
    private Date dateOfBirth;
}
