package com.app.clubmatrix.services.dto;

import com.app.clubmatrix.models.PositionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmployeeRegistrationDTO {

  private String name;
  private String address;
  private String phone;
  private String email;
  private PositionType position;
  private Integer salary;
  private CredentialsDTO credentials;
}
