package com.app.clubmatrix.gui.windows.manager.panels.models;

import com.app.clubmatrix.models.Activity;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ActivityTable extends AbstractTableModel {

  private final List<Activity> activities;

  private final String[] columnNames = {
    "ID",
    "Name",
    "Description",
    "Age Group",
    "Skill Level",
  };

  public ActivityTable(List<Activity> activities) {
    this.activities = activities;
  }

  @Override
  public int getRowCount() {
    return activities.size();
  }

  @Override
  public int getColumnCount() {
    return columnNames.length;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Activity activity = activities.get(rowIndex);
    return switch (columnIndex) {
      case 0 -> activity.getId();
      case 1 -> activity.getName();
      case 2 -> activity.getDescription();
      case 3 -> activity.getAgeGroup();
      case 4 -> activity.getSkillLevel();
      default -> null;
    };
  }

  @Override
  public String getColumnName(int column) {
    return columnNames[column];
  }

  public Activity getActivityAt(int rowIndex) {
    return activities.get(rowIndex);
  }
}
