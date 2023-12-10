package com.app.clubmatrix.gui.windows.manager.dialogs;

import com.app.clubmatrix.gui.components.*;
import com.app.clubmatrix.models.Employee;
import com.app.clubmatrix.models.PositionType;
import com.app.clubmatrix.services.EmployeeService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EditEmployeeDialog extends JDialog {

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
        super(parent, "Edit Employee", true);
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
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(saveButton, gbc);

        gbc.gridy++;
        panel.add(cancelButton, gbc);

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