package com.app.clubmatrix.gui.windows.manager.dialogs;

import com.app.clubmatrix.gui.components.*;
import com.app.clubmatrix.models.Employee;
import com.app.clubmatrix.models.PositionType;
import com.app.clubmatrix.services.EmployeeService;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EditEmployeeDialog extends Dialog {

    private final EmployeeService employeeService;
    private final Employee employee;

    private JTextField nameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField emailField;
    private JComboBox<PositionType> positionField;
    private JTextField salaryField;
    private JButton saveButton;
    private JButton cancelButton;

    public EditEmployeeDialog(JFrame parent, EmployeeService employeeService, Employee employee) {
        super(parent, "Edit Employee");
        this.employeeService = employeeService;
        this.employee = employee;

        initializeComponents();
        loadEmployeeData();
        setupLayout();
    }

    private void initializeComponents() {
        nameField = new NameInput(20);
        addressField = new AddressInput(20);
        phoneField = new PhoneInput(20);
        emailField = new EmailInput(20);
        positionField = new PositionInput();
        salaryField = new SalaryInput(20);

        saveButton = new JButton("Save");
        saveButton.addActionListener(this::saveEmployee);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> setVisible(false));
    }

    private void loadEmployeeData() {
        nameField.setText(employee.getName());
        addressField.setText(employee.getAddress());
        phoneField.setText(employee.getPhone());
        emailField.setText(employee.getEmail());
        positionField.setSelectedItem(employee.getPosition());
        salaryField.setText(employee.getSalary().toString());
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
        addComponent(saveButton, 0, 8, 1);
        addComponent(cancelButton, 1, 8, 1);

        add(panel);
        pack();
    }

    private void saveEmployee(ActionEvent event) {
        employee.setName(nameField.getText());
        employee.setAddress(addressField.getText());
        employee.setPhone(phoneField.getText());
        employee.setEmail(emailField.getText());
        employee.setPosition((PositionType) positionField.getSelectedItem());
        try {
            employee.setSalary(Integer.parseInt(salaryField.getText().replaceAll("\\D+", "")));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid salary format", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            employeeService.updateEmployee(employee);
            JOptionPane.showMessageDialog(this, "Employee updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to update employee: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        setVisible(false);
    }
}