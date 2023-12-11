package com.app.clubmatrix.services.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DependentRegistrationDTO {

  private String name;
  private String memberUsername;
  private String relationship;
  private Date dateOfBirth;
}
