package com.app.clubmatrix.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CredentialsDTO {

  private String username;
  private char[] password;
}
