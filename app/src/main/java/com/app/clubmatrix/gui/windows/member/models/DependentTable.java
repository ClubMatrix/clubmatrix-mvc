package com.app.clubmatrix.gui.windows.customer.panels.models;

import com.app.clubmatrix.models.Dependent; // Assuming Dependent class exists and is similar to Member class

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class DependentTable extends AbstractTableModel {

    private final List<Dependent> dependents;
    private final String[] columnNames = {"ID", "Name", "Address", "Phone", "Email", "MemberID"}; // Assuming dependents have a reference to a member through MemberID

    public DependentTable(List<Dependent> dependents) {
        this.dependents = dependents;
    }

    @Override
    public int getRowCount() {
        return dependents.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Dependent dependent = dependents.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> dependent.getId();
            case 1 -> dependent.getName();
            case 2 -> dependent.getAddress();
            case 3 -> dependent.getPhone();
            case 4 -> dependent.getEmail();
            case 5 -> dependent.getMemberID(); // Assuming there's a method to get the associated member's ID
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Dependent getDependentAt(int rowIndex) {
        return dependents.get(rowIndex);
    }
}