package com.app.clubmatrix.gui.windows.manager.panels;

import com.app.clubmatrix.gui.windows.manager.dialogs.AddMemberDialog;
import com.app.clubmatrix.gui.windows.manager.dialogs.EditMemberDialog;
import com.app.clubmatrix.gui.windows.manager.panels.models.MemberTable;
import com.app.clubmatrix.models.Member;
import com.app.clubmatrix.services.MemberService;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;

public class MemberManagementPanel extends JPanel {

  private final MemberService memberService;
  private JTable memberTable;

  public MemberManagementPanel(MemberService memberService) {
    this.memberService = memberService;
    initializePanel();
    loadMembers();
  }

  private void initializePanel() {
    setLayout(new BorderLayout());

    memberTable = new JTable();
    JScrollPane scrollPane = new JScrollPane(memberTable);
    add(scrollPane, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    JButton addButton = new JButton("Add Member");
    JButton editButton = new JButton("Edit Member");
    JButton deleteButton = new JButton("Delete Member");

    addButton.addActionListener(this::addMember);
    editButton.addActionListener(this::editMember);
    deleteButton.addActionListener(this::deleteMember);

    buttonPanel.add(addButton);
    buttonPanel.add(editButton);
    buttonPanel.add(deleteButton);

    add(buttonPanel, BorderLayout.SOUTH);
  }

  private void loadMembers() {
    List<Member> members = memberService.getAllMembers();
    MemberTable memberTableModel = new MemberTable(members);
    memberTable.setModel(memberTableModel);
  }

  private void addMember(ActionEvent event) {
    AddMemberDialog addMemberDialog = new AddMemberDialog(
      (JFrame) JFrame.getFrames()[0],
      memberService
    );
    addMemberDialog.setVisible(true);
    loadMembers();
  }

  private void editMember(ActionEvent event) {
    int selectedRow = memberTable.getSelectedRow();
    if (selectedRow >= 0) {
      Member selectedMember =
        ((MemberTable) memberTable.getModel()).getMemberAt(selectedRow);
      EditMemberDialog editMemberDialog = new EditMemberDialog(
        (JFrame) JFrame.getFrames()[0],
        memberService,
        selectedMember
      );
      editMemberDialog.setVisible(true);
      loadMembers();
    } else {
      JOptionPane.showMessageDialog(
        this,
        "Please select a member to edit.",
        "No Member Selected",
        JOptionPane.WARNING_MESSAGE
      );
    }
  }

  private void deleteMember(ActionEvent event) {
    int selectedRow = memberTable.getSelectedRow();
    if (selectedRow >= 0) {
      Member selectedMember =
        ((MemberTable) memberTable.getModel()).getMemberAt(selectedRow);

      int confirm = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to delete member: " +
        selectedMember.getName() +
        "?",
        "Confirm Deletion",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE
      );

      if (confirm == JOptionPane.YES_OPTION) {
        memberService.deleteMember(selectedMember);
        loadMembers();
      }
    } else {
      JOptionPane.showMessageDialog(
        this,
        "Please select a member to delete.",
        "No Member Selected",
        JOptionPane.WARNING_MESSAGE
      );
    }
  }
}
