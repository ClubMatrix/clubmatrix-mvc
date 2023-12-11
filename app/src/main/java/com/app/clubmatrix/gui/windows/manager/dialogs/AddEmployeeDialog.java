package com.app.clubmatrix.gui.windows.manager.dialogs;

import com.app.clubmatrix.gui.components.*;
import com.app.clubmatrix.models.PositionType;
import com.app.clubmatrix.services.EmployeeService;
import com.app.clubmatrix.services.dto.CredentialsDTO;
import com.app.clubmatrix.services.dto.EmployeeRegistrationDTO;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class AddEmployeeDialog extends Dialog {

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
    super(parent, "Add Employee");
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

    saveButton = new Button("Save", this::saveEmployee);
    cancelButton = new Button("Cancel", e -> setVisible(false));
  }

  private void setupLayout() {
    addComponent(new JLabel("Name:"), 0, 0, 1);
    addComponent(nameField, 1, 0, 1);
    addComponent(new JLabel("Address:"), 0, 1, 1);
    addComponent(addressField, 1, 1, 1);
    addComponent(new JLabel("Phone:"), 0, 2, 1);
    addComponent(phoneField, 1, 2, 1);
    addComponent(new JLabel("Email:"), 0, 3, 1);
    addComponent(emailField, 1, 3, 1);
    addComponent(new JLabel("Position:"), 0, 4, 1);
    addComponent(positionField, 1, 4, 1);
    addComponent(new JLabel("Salary:"), 0, 5, 1);
    addComponent(salaryField, 1, 5, 1);
    addComponent(new JLabel("Username:"), 0, 6, 1);
    addComponent(usernameField, 1, 6, 1);
    addComponent(new JLabel("Password:"), 0, 7, 1);
    addComponent(passwordField, 1, 7, 1);
    addComponent(saveButton, 0, 8, 1);
    addComponent(cancelButton, 1, 8, 1);

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
      JOptionPane.showMessageDialog(
        this,
        "Employee added successfully",
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
