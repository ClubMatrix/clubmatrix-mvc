package com.app.clubmatrix.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberRegistrationDTO {

    private String name;
    private String address;
    private String phone;
    private String email;
    private CredentialsDTO credentials;
}
