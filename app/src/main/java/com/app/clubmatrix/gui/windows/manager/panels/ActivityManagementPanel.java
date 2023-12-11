package com.app.clubmatrix.gui.windows.manager.panels;

import com.app.clubmatrix.gui.windows.manager.dialogs.AddActivityDialog;
import com.app.clubmatrix.gui.windows.manager.dialogs.EditActivityDialog;
import com.app.clubmatrix.gui.windows.manager.panels.models.ActivityTable;
import com.app.clubmatrix.models.Activity;
import com.app.clubmatrix.services.ActivityService;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;

public class ActivityManagementPanel extends JPanel {

  private final ActivityService ActivityService;
  private JTable ActivityTable;

  public ActivityManagementPanel(ActivityService ActivityService) {
    this.ActivityService = ActivityService;
    initializePanel();
    loadActivities();
  }

  private void initializePanel() {
    setLayout(new BorderLayout());

    ActivityTable = new JTable();
    JScrollPane scrollPane = new JScrollPane(ActivityTable);
    add(scrollPane, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    JButton addButton = new JButton("Add Activity");
    JButton editButton = new JButton("Edit Activity");
    JButton deleteButton = new JButton("Delete Activity");

    addButton.addActionListener(this::addActivity);
    editButton.addActionListener(this::editActivity);
    deleteButton.addActionListener(this::deleteActivity);

    buttonPanel.add(addButton);
    buttonPanel.add(editButton);
    buttonPanel.add(deleteButton);

    add(buttonPanel, BorderLayout.SOUTH);
  }

  private void loadActivities() {
    List<Activity> Activities = ActivityService.getAllActivities();
    ActivityTable ActivityTableModel = new ActivityTable(Activities);
    ActivityTable.setModel(ActivityTableModel);
  }

  private void addActivity(ActionEvent event) {
    AddActivityDialog addActivityDialog = new AddActivityDialog(
      (JFrame) JFrame.getFrames()[0],
      ActivityService
    );
    addActivityDialog.setVisible(true);
    loadActivities();
  }

  private void editActivity(ActionEvent event) {
    int selectedRow = ActivityTable.getSelectedRow();
    if (selectedRow >= 0) {
      Activity selectedActivity =
        ((ActivityTable) ActivityTable.getModel()).getActivityAt(selectedRow);
      EditActivityDialog editActivityDialog = new EditActivityDialog(
        (JFrame) JFrame.getFrames()[0],
        ActivityService,
        selectedActivity
      );
      editActivityDialog.setVisible(true);
      loadActivities();
    } else {
      JOptionPane.showMessageDialog(
        this,
        "Please select a Activity to edit.",
        "No Activity Selected",
        JOptionPane.WARNING_MESSAGE
      );
    }
  }

  private void deleteActivity(ActionEvent event) {
    int selectedRow = ActivityTable.getSelectedRow();
    if (selectedRow >= 0) {
      Activity selectedActivity =
        ((ActivityTable) ActivityTable.getModel()).getActivityAt(selectedRow);

      int confirm = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to delete Activity: " +
        selectedActivity.getName() +
        "?",
        "Confirm Deletion",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE
      );

      if (confirm == JOptionPane.YES_OPTION) {
        ActivityService.deleteActivity(selectedActivity);
        loadActivities();
      }
    } else {
      JOptionPane.showMessageDialog(
        this,
        "Please select a Activity to delete.",
        "No Activity Selected",
        JOptionPane.WARNING_MESSAGE
      );
    }
  }
}
