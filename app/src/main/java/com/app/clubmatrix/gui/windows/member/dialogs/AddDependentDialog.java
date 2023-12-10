package com.app.clubmatrix.gui.windows.manager.dialogs;

import com.app.clubmatrix.gui.components.*;
import com.app.clubmatrix.services.DependentService;
import com.app.clubmatrix.services.dto.DependentRegistrationDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddDependentDialog extends JDialog {

    private final DependentService dependentService;

    private JTextField nameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField emailField;
    private JComboBox<Member> memberComboBox; // Assuming you have a Member class that can be displayed in a combo box
    private JButton saveButton;
    private JButton cancelButton;

    public AddDependentDialog(JFrame parent, DependentService dependentService, java.util.List<Member> members) {
        super(parent, "Add Dependent", true);
        this.dependentService = dependentService;

        initializeComponents(members);
        setupLayout();
    }

    private void initializeComponents(java.util.List<Member> members) {
        nameField = new NameInput(20);
        addressField = new AddressInput(20);
        phoneField = new PhoneInput(20);
        emailField = new EmailInput(20);
        memberComboBox = new JComboBox<>(new DefaultComboBoxModel<>(members.toArray(new Member[0])));

        saveButton = new JButton("Save");
        saveButton.addActionListener(this::saveDependent);
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
        panel.add(new JLabel("Address:"), gbc);
        gbc.gridx++;
        panel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Phone:"), gbc);
        gbc.gridx++;
        panel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx++;
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Member:"), gbc); // This is the additional field for selecting a member
        gbc.gridx++;
        panel.add(memberComboBox, gbc);

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

    private void saveDependent(ActionEvent event) {
        Member selectedMember = (Member) memberComboBox.getSelectedItem();
        if (selectedMember == null) {
            JOptionPane.showMessageDialog(this, "Please select a member.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DependentRegistrationDTO registrationDTO = new DependentRegistrationDTO(
                nameField.getText(),
                addressField.getText(),
                phoneField.getText(),
                emailField.getText(),
                selectedMember.getId() // Assuming DependentRegistrationDTO takes the member's ID
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