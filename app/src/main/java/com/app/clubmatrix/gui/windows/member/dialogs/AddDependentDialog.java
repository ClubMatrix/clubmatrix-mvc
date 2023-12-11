package com.app.clubmatrix.gui.windows.manager.dialogs;

import com.app.clubmatrix.gui.components.DateInput;
import com.app.clubmatrix.gui.components.Dialog;
import com.app.clubmatrix.gui.components.NameInput;
import com.app.clubmatrix.services.DependentService;
import com.app.clubmatrix.services.dto.DependentRegistrationDTO;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddDependentDialog extends Dialog {

    private final DependentService dependentService;
    private final String memberUsername;

    private JTextField nameField;
    private JTextField relationshipField;
    private DateInput dateOfBirthField;
    private JButton saveButton;
    private JButton cancelButton;

    public AddDependentDialog(JFrame parent, DependentService dependentService, String memberUsername) {
        super(parent, "Add Dependent");
        this.dependentService = dependentService;
        this.memberUsername = memberUsername;

        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        nameField = new NameInput(20);
        relationshipField = new JTextField(20);
        dateOfBirthField = new DateInput(20);

        saveButton = new JButton("Save");
        saveButton.addActionListener(this::saveMember);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> setVisible(false));
    }

    private void setupLayout() {
        addComponent(new JLabel("Name:"), 0, 0, 1);
        addComponent(nameField, 1, 0, 1);
        addComponent(new JLabel("Relationship:"), 0, 1, 1);
        addComponent(relationshipField, 1, 1, 1);
        addComponent(new JLabel("Date of Birth:"), 0, 2, 1);
        addComponent(dateOfBirthField, 1, 2, 1);
        addComponent(saveButton, 0, 3, 1);
        addComponent(cancelButton, 1, 3, 1);

        add(panel);
        pack();
    }

    private void saveMember(ActionEvent event) {
        DependentRegistrationDTO registrationDTO = new DependentRegistrationDTO(
                nameField.getText(),
                memberUsername,
                relationshipField.getText(),
                dateOfBirthField.getDate()
        );

        try {
            dependentService.registerDependent(registrationDTO);
            JOptionPane.showMessageDialog(this, "Dependent added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        setVisible(false);
    }
}