package com.app.clubmatrix.gui.windows.manager.dialogs;

import com.app.clubmatrix.gui.components.AgeGroupInput;
import com.app.clubmatrix.gui.components.Dialog;
import com.app.clubmatrix.gui.components.SkillLevelInput;
import com.app.clubmatrix.models.Activity;
import com.app.clubmatrix.models.AgeGroupType;
import com.app.clubmatrix.models.SkillLevelType;
import com.app.clubmatrix.services.ActivityService;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class EditActivityDialog extends Dialog {

  private final ActivityService activityService;
  private final Activity activity;
  private JTextField nameField;
  private JTextArea descriptionField;
  private JComboBox<AgeGroupType> ageGroupField;
  private JComboBox<SkillLevelType> skillLevelField;
  private JButton saveButton;
  private JButton cancelButton;

  public EditActivityDialog(
    JFrame parent,
    ActivityService activityService,
    Activity activity
  ) {
    super(parent, "Add Activity");
    this.activityService = activityService;
    this.activity = activity;
    initializeComponents();
    loadActivityData(activity);
    setupLayout();
  }

  private void initializeComponents() {
    nameField = new JTextField(20);
    descriptionField = new JTextArea(5, 20);
    ageGroupField = new AgeGroupInput();
    skillLevelField = new SkillLevelInput();

    saveButton = new JButton("Save");
    saveButton.addActionListener(this::saveActivity);
    cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(e -> setVisible(false));
  }

  private void loadActivityData(Activity activity) {
    nameField.setText(activity.getName());
    descriptionField.setText(activity.getDescription());
    ageGroupField.setSelectedItem(activity.getAgeGroup());
    skillLevelField.setSelectedItem(activity.getSkillLevel());
  }

  private void setupLayout() {
    addComponent(new JLabel("Name:"), 0, 0, 1);
    addComponent(nameField, 1, 0, 2);
    addComponent(new JLabel("Description:"), 0, 1, 1);
    addComponent(descriptionField, 1, 1, 2);
    addComponent(new JLabel("Age Group:"), 0, 2, 1);
    addComponent(ageGroupField, 1, 2, 2);
    addComponent(new JLabel("Skill Level:"), 0, 3, 1);
    addComponent(skillLevelField, 1, 3, 2);
    addComponent(saveButton, 0, 4, 1);
    addComponent(cancelButton, 1, 4, 2);

    add(panel);
    pack();
  }

  private void saveActivity(ActionEvent event) {
    activity.setName(nameField.getText());
    activity.setDescription(descriptionField.getText());
    activity.setAgeGroup((AgeGroupType) ageGroupField.getSelectedItem());
    activity.setSkillLevel((SkillLevelType) skillLevelField.getSelectedItem());

    try {
      activityService.updateActivity(activity);
      JOptionPane.showMessageDialog(
        this,
        "Activity updated successfully",
        "Success",
        JOptionPane.INFORMATION_MESSAGE
      );
    } catch (Exception e) {
      JOptionPane.showMessageDialog(
        this,
        e.getMessage(),
        "Error",
        JOptionPane.ERROR_MESSAGE
      );
    }
    setVisible(false);
  }
}
