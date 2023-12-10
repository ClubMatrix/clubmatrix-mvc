package com.app.clubmatrix.gui.windows.manager.dialogs;

import com.app.clubmatrix.gui.components.AddressInput;
import com.app.clubmatrix.gui.components.EmailInput;
import com.app.clubmatrix.gui.components.NameInput;
import com.app.clubmatrix.gui.components.PhoneInput;
import com.app.clubmatrix.models.Member;
import com.app.clubmatrix.services.MemberService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EditMemberDialog extends JDialog {

    private final MemberService memberService;
    private final Member member;

    private JTextField nameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField emailField;
    private JButton saveButton;
    private JButton cancelButton;

    public EditMemberDialog(JFrame parent, MemberService memberService, Member member) {
        super(parent, "Edit Member", true);
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
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(saveButton, gbc);

        gbc.gridy++;
        panel.add(cancelButton, gbc);

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