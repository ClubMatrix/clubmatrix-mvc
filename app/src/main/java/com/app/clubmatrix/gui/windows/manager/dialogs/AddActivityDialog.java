package com.app.clubmatrix.gui.windows.manager.dialogs;

import com.app.clubmatrix.gui.components.AgeGroupInput;
import com.app.clubmatrix.gui.components.Button;
import com.app.clubmatrix.gui.components.Dialog;
import com.app.clubmatrix.gui.components.SkillLevelInput;
import com.app.clubmatrix.models.AgeGroupType;
import com.app.clubmatrix.models.SkillLevelType;
import com.app.clubmatrix.services.ActivityService;
import com.app.clubmatrix.services.dto.ActivityRegistrationDTO;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddActivityDialog extends Dialog {

    private final ActivityService activityService;

    private JTextField nameField;
    private JTextArea descriptionField;
    private JComboBox<AgeGroupType> ageGroupField;
    private JComboBox<SkillLevelType> skillLevelField;
    private JButton saveButton;
    private JButton cancelButton;

    public AddActivityDialog(JFrame parent, ActivityService activityService) {
        super(parent, "Add Activity");
        this.activityService = activityService;
        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        nameField = new JTextField(20);
        descriptionField = new JTextArea(5, 20);
        ageGroupField = new AgeGroupInput();
        skillLevelField = new SkillLevelInput();

        saveButton = new Button("Save", this::saveActivity);
        cancelButton = new Button("Cancel", e -> setVisible(false));
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
        ActivityRegistrationDTO registrationDTO = new ActivityRegistrationDTO(
                nameField.getText(),
                descriptionField.getText(),
                (AgeGroupType) ageGroupField.getSelectedItem(),
                (SkillLevelType) skillLevelField.getSelectedItem()
        );

        try {
            activityService.registerActivity(registrationDTO);
            JOptionPane.showMessageDialog(this, "Activity added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        setVisible(false);
    }
}