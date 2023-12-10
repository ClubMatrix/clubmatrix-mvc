package com.app.clubmatrix.gui.windows.manager.dialogs;

import com.app.clubmatrix.gui.components.*;
import com.app.clubmatrix.services.MemberService;
import com.app.clubmatrix.services.dto.CredentialsDTO;
import com.app.clubmatrix.services.dto.MemberRegistrationDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddMemberDialog extends JDialog {

    private final MemberService memberService;

    private JTextField nameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton saveButton;
    private JButton cancelButton;

    public AddMemberDialog(JFrame parent, MemberService memberService) {
        super(parent, "Add Member", true);
        this.memberService = memberService;

        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        nameField = new NameInput(20);
        addressField = new AddressInput(20);
        phoneField = new PhoneInput(20);
        emailField = new EmailInput(20);
        usernameField = new UsernameInput(20);
        passwordField = new PasswordInput(20);

        saveButton = new JButton("Save");
        saveButton.addActionListener(this::saveMember);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> setVisible(false));
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
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx++;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx++;
        panel.add(passwordField, gbc);

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
        MemberRegistrationDTO registrationDTO = new MemberRegistrationDTO(
                nameField.getText(),
                addressField.getText(),
                phoneField.getText(),
                emailField.getText(),
                new CredentialsDTO(usernameField.getText(), passwordField.getPassword())
        );

        try {
            memberService.registerMember(registrationDTO);
            JOptionPane.showMessageDialog(this, "Member added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        setVisible(false);
    }
}