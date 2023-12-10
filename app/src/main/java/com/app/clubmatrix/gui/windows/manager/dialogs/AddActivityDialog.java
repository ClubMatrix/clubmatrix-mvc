package com.app.clubmatrix.gui.windows.manager.dialogs;

import com.app.clubmatrix.gui.components.AgeGroupInput;
import com.app.clubmatrix.gui.components.SkillLevelInput;
import com.app.clubmatrix.models.AgeGroupType;
import com.app.clubmatrix.models.SkillLevelType;
import com.app.clubmatrix.services.ActivityService;
import com.app.clubmatrix.services.dto.ActivityRegistrationDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddActivityDialog extends JDialog {

    private final ActivityService activityService;

    private JTextField nameField;
    private JTextArea descriptionField;
    private JComboBox<AgeGroupType> ageGroupField;
    private JComboBox<SkillLevelType> skillLevelField;
    private JButton saveButton;
    private JButton cancelButton;

    public AddActivityDialog(JFrame parent, ActivityService activityService) {
        super(parent, "Add Activity", true);
        this.activityService = activityService;
        initializeComponents();
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

    private void setupLayout() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        panel.add(new JLabel("Name:"), gbc);
        gbc.gridx++;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Description:"), gbc);
        gbc.gridx++;
        panel.add(descriptionField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Age Group:"), gbc);
        gbc.gridx++;
        panel.add(ageGroupField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Skill Level:"), gbc);
        gbc.gridx++;
        panel.add(skillLevelField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(saveButton, gbc);

        gbc.gridy++;
        panel.add(cancelButton, gbc);

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