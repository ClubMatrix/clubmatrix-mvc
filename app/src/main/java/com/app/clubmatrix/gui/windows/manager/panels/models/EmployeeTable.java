package com.app.clubmatrix.gui.windows.manager.panels.models;

import com.app.clubmatrix.models.Employee;
import com.app.clubmatrix.utils.Format;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class EmployeeTable extends AbstractTableModel {

    private final List<Employee> employees;
    private final String[] columnNames = {"ID", "Name", "Address", "Phone", "Email", "Position", "Salary"};

    public EmployeeTable(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public int getRowCount() {
        return employees.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee employee = employees.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> employee.getId();
            case 1 -> employee.getName();
            case 2 -> employee.getAddress();
            case 3 -> employee.getPhone();
            case 4 -> employee.getEmail();
            case 5 -> employee.getPosition().getValue();
            case 6 -> Format.formatMoney(employee.getSalary());
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Employee getEmployeeAt(int rowIndex) {
        return employees.get(rowIndex);
    }
}