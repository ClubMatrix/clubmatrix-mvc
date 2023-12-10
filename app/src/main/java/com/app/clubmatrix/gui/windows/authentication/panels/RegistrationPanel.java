package com.app.clubmatrix.gui.windows.authentication.panels;

import com.app.clubmatrix.gui.components.*;
import com.app.clubmatrix.models.PositionType;
import com.app.clubmatrix.services.dto.CredentialsDTO;
import com.app.clubmatrix.services.dto.EmployeeRegistrationDTO;
import com.app.clubmatrix.services.dto.MemberRegistrationDTO;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class RegistrationPanel extends JPanel {

    private final JTextField nameField;
    private final JTextField addressField;
    private final JTextField phoneField;
    private final JTextField emailField;
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JLabel positionLabel;
    private final JComboBox<PositionType> positionField;
    private final JLabel salaryLabel;
    private final JTextField salaryField;
    private final JCheckBox isEmployeeCheckbox;

    public RegistrationPanel(Consumer<MemberRegistrationDTO> onRegistrationAttempt, Consumer<EmployeeRegistrationDTO> onEmployeeRegistrationAttempt) {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;

        addLabelAndTextField("Name:", nameField = new UsernameInput(20), constraints);

        addLabelAndTextField("Address:", addressField = new AddressInput(20), constraints);

        addLabelAndTextField("Phone:", phoneField = new PhoneInput(20), constraints);

        addLabelAndTextField("Email:", emailField = new EmailInput(20), constraints);

        addLabelAndTextField("Username:", usernameField = new UsernameInput(20), constraints);

        addLabelAndTextField("Password:", passwordField = new PasswordInput(20), constraints);

        positionLabel = addLabelAndTextField("Position:", positionField = new PositionInput(), constraints);
        salaryLabel = addLabelAndTextField("Salary:", salaryField = new SalaryInput(20), constraints);

        isEmployeeCheckbox = new JCheckBox("Register as Employee");
        isEmployeeCheckbox.addActionListener(e -> toggleFieldsBasedOnRole());
        constraints.gridy++;
        add(isEmployeeCheckbox, constraints);

        JButton submitButton = getjButton(onRegistrationAttempt, onEmployeeRegistrationAttempt);
        constraints.gridy++;
        add(submitButton, constraints);

        toggleFieldsBasedOnRole();
    }

    private JButton getjButton(Consumer<MemberRegistrationDTO> onRegistrationAttempt, Consumer<EmployeeRegistrationDTO> onEmployeeRegistrationAttempt) {
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {

            if (isEmployeeCheckbox.isSelected()) {
                onEmployeeRegistrationAttempt.accept(
                        new EmployeeRegistrationDTO(
                                nameField.getText(),
                                addressField.getText(),
                                phoneField.getText(),
                                emailField.getText(),
                                (PositionType) positionField.getSelectedItem(),
                                Integer.parseInt(salaryField.getText().replaceAll("\\D+", "")),
                                new CredentialsDTO(
                                        usernameField.getText(),
                                        passwordField.getPassword()
                                )
                        )
                );

                return;
            }

            onRegistrationAttempt.accept(
                new MemberRegistrationDTO(
                        nameField.getText(),
                        addressField.getText(),
                        phoneField.getText(),
                        emailField.getText(),
                        new CredentialsDTO(
                                usernameField.getText(),
                                passwordField.getPassword()
                        )
                )
            );
        });

        return submitButton;
    }

    private void toggleFieldsBasedOnRole() {
        boolean isEmployee = isEmployeeCheckbox.isSelected();
        positionLabel.setVisible(isEmployee);
        positionField.setVisible(isEmployee);
        salaryLabel.setVisible(isEmployee);
        salaryField.setVisible(isEmployee);
    }

    private JLabel addLabelAndTextField(String labelText, JComponent field, GridBagConstraints constraints) {
        JLabel label = new JLabel(labelText);
        add(label, constraints);
        constraints.gridx++;
        add(field, constraints);
        constraints.gridx = 0;
        constraints.gridy++;

        return label;
    }
}