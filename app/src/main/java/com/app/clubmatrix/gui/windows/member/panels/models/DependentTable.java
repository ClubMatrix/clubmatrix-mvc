package com.app.clubmatrix.gui.windows.member.panels.models;

import com.app.clubmatrix.models.Dependent;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class DependentTable extends AbstractTableModel {

  private final List<Dependent> dependents;
  private final String[] columnNames = {
    "ID",
    "Name",
    "Relationship",
    "Date of Birth",
    "Member",
  };

  public DependentTable(List<Dependent> dependents) {
    this.dependents = dependents;
  }

  @Override
  public int getRowCount() {
    return dependents.size();
  }

  @Override
  public int getColumnCount() {
    return columnNames.length;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Dependent dependent = dependents.get(rowIndex);
    return switch (columnIndex) {
      case 0 -> dependent.getId();
      case 1 -> dependent.getName();
      case 2 -> dependent.getRelationship();
      case 3 -> dependent.getDateOfBirth();
      case 4 -> dependent.getMember().getUser().getUsername();
      default -> null;
    };
  }

  @Override
  public String getColumnName(int column) {
    return columnNames[column];
  }

  public Dependent getDependentAt(int rowIndex) {
    return dependents.get(rowIndex);
  }
}
