package com.app.clubmatrix.gui.windows.manager.dialogs;

import com.app.clubmatrix.gui.components.*;
import com.app.clubmatrix.models.PositionType;
import com.app.clubmatrix.services.EmployeeService;
import com.app.clubmatrix.services.dto.CredentialsDTO;
import com.app.clubmatrix.services.dto.EmployeeRegistrationDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddEmployeeDialog extends JDialog {

    private final EmployeeService employeeService;

    private JTextField nameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField emailField;
    private JComboBox<PositionType> positionField;
    private JTextField salaryField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton saveButton;
    private JButton cancelButton;

    public AddEmployeeDialog(JFrame parent, EmployeeService employeeService) {
        super(parent, "Add Employee", true);
        this.employeeService = employeeService;
        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        nameField = new NameInput(20);
        addressField = new AddressInput(20);
        phoneField = new PhoneInput(20);
        emailField = new EmailInput(20);
        positionField = new PositionInput();
        salaryField = new SalaryInput(20);
        usernameField = new UsernameInput(20);
        passwordField = new PasswordInput(20);

        saveButton = new JButton("Save");
        saveButton.addActionListener(this::saveEmployee);
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
        panel.add(new JLabel("Position:"), gbc);
        gbc.gridx++;
        panel.add(positionField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Salary:"), gbc);
        gbc.gridx++;
        panel.add(salaryField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx++;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx++;
        panel.add(passwordField, gbc);

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

    private void saveEmployee(ActionEvent event) {
        EmployeeRegistrationDTO registrationDTO = new EmployeeRegistrationDTO(
                nameField.getText(),
                addressField.getText(),
                phoneField.getText(),
                emailField.getText(),
                (PositionType) positionField.getSelectedItem(),
                Integer.parseInt(salaryField.getText().replaceAll("\\D+", "")),
                new CredentialsDTO(usernameField.getText(), passwordField.getPassword())
        );

        try {
            employeeService.registerEmployee(registrationDTO);
            JOptionPane.showMessageDialog(this, "Employee added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        setVisible(false);
    }
}