package com.app.clubmatrix.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderRegistrationDTO {

  private Integer amount;
  private String memberUsername;
  private String employeeUsername;
}
