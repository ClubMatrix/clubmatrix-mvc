package com.app.clubmatrix.gui.windows.manager.panels.models;

import com.app.clubmatrix.models.Member;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MemberTable extends AbstractTableModel {

    private final List<Member> members;
    private final String[] columnNames = {"ID", "Name", "Address", "Phone", "Email"};

    public MemberTable(List<Member> members) {
        this.members = members;
    }

    @Override
    public int getRowCount() {
        return members.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Member member = members.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> member.getId();
            case 1 -> member.getName();
            case 2 -> member.getAddress();
            case 3 -> member.getPhone();
            case 4 -> member.getEmail();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Member getMemberAt(int rowIndex) {
        return members.get(rowIndex);
    }
}