package com.app.clubmatrix.gui.windows.manager.panels;

import com.app.clubmatrix.gui.windows.manager.dialogs.AddEmployeeDialog;
import com.app.clubmatrix.gui.windows.manager.dialogs.EditEmployeeDialog;
import com.app.clubmatrix.gui.windows.manager.panels.models.EmployeeTable;
import com.app.clubmatrix.models.Employee;
import com.app.clubmatrix.services.EmployeeService;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;

public class EmployeeManagementPanel extends JPanel {

  private final EmployeeService employeeService;
  private JTable employeeTable;

  public EmployeeManagementPanel(EmployeeService employeeService) {
    this.employeeService = employeeService;
    initializePanel();
    loadEmployees();
  }

  private void initializePanel() {
    setLayout(new BorderLayout());

    employeeTable = new JTable();
    JScrollPane scrollPane = new JScrollPane(employeeTable);
    add(scrollPane, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    JButton addButton = new JButton("Add Employee");
    JButton editButton = new JButton("Edit Employee");
    JButton deleteButton = new JButton("Delete Employee");

    addButton.addActionListener(this::addEmployee);
    editButton.addActionListener(this::editEmployee);
    deleteButton.addActionListener(this::deleteEmployee);

    buttonPanel.add(addButton);
    buttonPanel.add(editButton);
    buttonPanel.add(deleteButton);

    add(buttonPanel, BorderLayout.SOUTH);
  }

  private void loadEmployees() {
    List<Employee> employees = employeeService.getAllEmployees();
    EmployeeTable employeeTableModel = new EmployeeTable(employees);
    employeeTable.setModel(employeeTableModel);
  }

  private void addEmployee(ActionEvent event) {
    AddEmployeeDialog addEmployeeDialog = new AddEmployeeDialog(
      (JFrame) SwingUtilities.getWindowAncestor(this),
      employeeService
    );
    addEmployeeDialog.setVisible(true);
    loadEmployees();
  }

  private void editEmployee(ActionEvent event) {
    int selectedRow = employeeTable.getSelectedRow();
    if (selectedRow >= 0) {
      Employee selectedEmployee =
        ((EmployeeTable) employeeTable.getModel()).getEmployeeAt(selectedRow);
      EditEmployeeDialog editEmployeeDialog = new EditEmployeeDialog(
        (JFrame) SwingUtilities.getWindowAncestor(this),
        employeeService,
        selectedEmployee
      );
      editEmployeeDialog.setVisible(true);
      loadEmployees();
    } else {
      JOptionPane.showMessageDialog(
        this,
        "Please select an employee to edit.",
        "No Employee Selected",
        JOptionPane.WARNING_MESSAGE
      );
    }
  }

  private void deleteEmployee(ActionEvent event) {
    int selectedRow = employeeTable.getSelectedRow();
    if (selectedRow >= 0) {
      Employee selectedEmployee =
        ((EmployeeTable) employeeTable.getModel()).getEmployeeAt(selectedRow);

      int confirm = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to delete employee: " +
        selectedEmployee.getName() +
        "?",
        "Confirm Deletion",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE
      );

      if (confirm == JOptionPane.YES_OPTION) {
        employeeService.deleteEmployee(selectedEmployee);
        loadEmployees();
      }
    } else {
      JOptionPane.showMessageDialog(
        this,
        "Please select an employee to delete.",
        "No Employee Selected",
        JOptionPane.WARNING_MESSAGE
      );
    }
  }
}
