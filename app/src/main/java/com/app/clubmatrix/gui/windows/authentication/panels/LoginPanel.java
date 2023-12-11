package com.app.clubmatrix.gui.windows.authentication.panels;

import com.app.clubmatrix.services.dto.CredentialsDTO;
import java.awt.*;
import java.util.function.Consumer;
import javax.swing.*;

public class LoginPanel extends JPanel {

  private final JTextField usernameField;
  private final JPasswordField passwordField;

  public LoginPanel(Consumer<CredentialsDTO> onLoginAttempt) {
    setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.gridx = 0;
    constraints.gridy = 0;

    addLabelAndField(
      "Username:",
      usernameField = new JTextField(20),
      constraints
    );
    addLabelAndField(
      "Password:",
      passwordField = new JPasswordField(20),
      constraints
    );

    JButton loginButton = new JButton("Login");
    loginButton.addActionListener(e ->
      onLoginAttempt.accept(
        new CredentialsDTO(usernameField.getText(), passwordField.getPassword())
      )
    );
    constraints.gridy++;
    add(loginButton, constraints);
  }

  private void addLabelAndField(
    String labelText,
    JComponent field,
    GridBagConstraints constraints
  ) {
    add(new JLabel(labelText), constraints);
    constraints.gridx++;
    add(field, constraints);
    constraints.gridx = 0;
    constraints.gridy++;
  }
}
