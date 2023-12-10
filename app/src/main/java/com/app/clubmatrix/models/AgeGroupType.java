package com.app.clubmatrix.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AgeGroupType {
  CHILDREN("Children"),
  TEENAGERS("Teenagers"),
  ADULTS("Adults"),
  SENIORS("Seniors");

  private final String value;
}
