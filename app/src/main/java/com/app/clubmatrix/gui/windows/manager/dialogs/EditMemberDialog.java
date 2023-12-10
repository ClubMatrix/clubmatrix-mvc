package com.app.clubmatrix.gui.windows.manager.dialogs;

import com.app.clubmatrix.gui.components.*;
import com.app.clubmatrix.models.Member;
import com.app.clubmatrix.services.MemberService;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EditMemberDialog extends Dialog {

    private final MemberService memberService;
    private final Member member;

    private JTextField nameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField emailField;
    private JButton saveButton;
    private JButton cancelButton;

    public EditMemberDialog(JFrame parent, MemberService memberService, Member member) {
        super(parent, "Edit Member");
        this.memberService = memberService;
        this.member = member;

        initializeComponents();
        loadMemberData();
        setupLayout();
    }

    private void initializeComponents() {
        nameField = new NameInput(20);
        addressField = new AddressInput(20);
        phoneField = new PhoneInput(20);
        emailField = new EmailInput(20);

        saveButton = new JButton("Save");
        saveButton.addActionListener(this::saveMember);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> setVisible(false));
    }

    private void loadMemberData() {
        nameField.setText(member.getName());
        addressField.setText(member.getAddress());
        phoneField.setText(member.getPhone());
        emailField.setText(member.getEmail());
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
        addComponent(saveButton, 0, 6, 1);
        addComponent(cancelButton, 1, 6, 1);

        add(panel);
        pack();
    }

    private void saveMember(ActionEvent event) {
        member.setName(nameField.getText());
        member.setAddress(addressField.getText());
        member.setPhone(phoneField.getText());
        member.setEmail(emailField.getText());

        try {
            memberService.updateMember(member);
            JOptionPane.showMessageDialog(this, "Member updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to update member: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        setVisible(false);
    }
}