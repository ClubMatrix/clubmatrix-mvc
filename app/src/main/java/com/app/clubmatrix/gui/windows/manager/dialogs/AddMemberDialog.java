package com.app.clubmatrix.gui.windows.manager.dialogs;

import com.app.clubmatrix.gui.components.*;
import com.app.clubmatrix.services.MemberService;
import com.app.clubmatrix.services.dto.CredentialsDTO;
import com.app.clubmatrix.services.dto.MemberRegistrationDTO;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class AddMemberDialog extends Dialog {

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
    super(parent, "Add Member");
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
    addComponent(new JLabel("Name:"), 0, 0, 1);
    addComponent(nameField, 1, 0, 1);
    addComponent(new JLabel("Address:"), 0, 1, 1);
    addComponent(addressField, 1, 1, 1);
    addComponent(new JLabel("Phone:"), 0, 2, 1);
    addComponent(phoneField, 1, 2, 1);
    addComponent(new JLabel("Email:"), 0, 3, 1);
    addComponent(emailField, 1, 3, 1);
    addComponent(new JLabel("Username:"), 0, 4, 1);
    addComponent(usernameField, 1, 4, 1);
    addComponent(new JLabel("Password:"), 0, 5, 1);
    addComponent(passwordField, 1, 5, 1);
    addComponent(saveButton, 0, 6, 1);
    addComponent(cancelButton, 1, 6, 1);

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
      JOptionPane.showMessageDialog(
        this,
        "Member added successfully",
        "Success",
        JOptionPane.INFORMATION_MESSAGE
      );
    } catch (Exception e) {
      JOptionPane.showMessageDialog(
        this,
        e.getMessage(),
        "Error",
        JOptionPane.ERROR_MESSAGE
      );
      return;
    }

    setVisible(false);
  }
}
