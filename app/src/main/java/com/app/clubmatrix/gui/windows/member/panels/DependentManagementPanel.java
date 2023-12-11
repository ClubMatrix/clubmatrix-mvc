package com.app.clubmatrix.gui.windows.member.panels;

import com.app.clubmatrix.gui.windows.manager.dialogs.AddDependentDialog;
import com.app.clubmatrix.gui.windows.member.dialogs.EditDependentDialog;
import com.app.clubmatrix.gui.windows.member.panels.models.DependentTable;
import com.app.clubmatrix.models.Dependent;
import com.app.clubmatrix.services.DependentService;
import com.app.clubmatrix.services.MemberService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class DependentManagementPanel extends JPanel {

    private final DependentService dependentService;
    private final MemberService memberService;
    private String username;
    private JTable dependentTable;

    public DependentManagementPanel(DependentService dependentService, MemberService memberService, String username) {
        this.dependentService = dependentService;
        this.memberService = memberService;
        this.username = username;
        initializePanel();
        loadDependents();
    }

    private void initializePanel() {
        setLayout(new BorderLayout());

        dependentTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(dependentTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Dependent");
        JButton editButton = new JButton("Edit Dependent");
        JButton deleteButton = new JButton("Delete Dependent");

        addButton.addActionListener(this::addDependent);
        editButton.addActionListener(this::editDependent);
        deleteButton.addActionListener(this::deleteDependent);

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadDependents() {
        List<Dependent> dependents = dependentService.getAllDependents();
        DependentTable dependentTableModel = new DependentTable(dependents);
        dependentTable.setModel(dependentTableModel);
    }

    public void setUsername(String username) {
        this.username = username;

        loadDependents();
    }

    private void addDependent(ActionEvent event) {
        AddDependentDialog addDependentDialog = new AddDependentDialog((JFrame) JFrame.getFrames()[0], dependentService, username);
        addDependentDialog.setVisible(true);
        loadDependents();
    }

    private void editDependent(ActionEvent event) {
        int selectedRow = dependentTable.getSelectedRow();
        if (selectedRow >= 0) {
            Dependent selectedDependent = ((DependentTable) dependentTable.getModel()).getDependentAt(selectedRow);
            EditDependentDialog editDependentDialog = new EditDependentDialog((JFrame) JFrame.getFrames()[0], dependentService, username, selectedDependent);
            editDependentDialog.setVisible(true);
            loadDependents();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a dependent to edit.", "No Dependent Selected", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteDependent(ActionEvent event) {
        int selectedRow = dependentTable.getSelectedRow();
        if (selectedRow >= 0) {
            Dependent selectedDependent = ((DependentTable) dependentTable.getModel()).getDependentAt(selectedRow);

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete dependent: " + selectedDependent.getName() + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirm == JOptionPane.YES_OPTION) {
                dependentService.deleteDependent(selectedDependent);
                loadDependents();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a dependent to delete.", "No Dependent Selected", JOptionPane.WARNING_MESSAGE);
        }
    }
}