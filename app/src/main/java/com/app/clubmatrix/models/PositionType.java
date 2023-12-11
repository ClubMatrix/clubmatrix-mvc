package com.app.clubmatrix.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PositionType {
  MANAGER("Manager"),
  INSTRUCTOR("Instructor"),
  CLERK("Clerk"),
  CAFETERIA_STAFF("Cafeteria Staff"),
  MAINTENANCE("Maintenance");

  private final String value;
}
