package com.app.clubmatrix.gui.components;

import javax.swing.*;

public class PasswordInput extends JPasswordField {

  public PasswordInput(int columns) {
    super(columns);
    setInputVerifier(new PasswordVerifier());
  }

  static class PasswordVerifier extends InputVerifier {

    @Override
    public boolean verify(JComponent input) {
      return (
        ((JPasswordField) input).getPassword().length > 0 &&
        ((JPasswordField) input).getPassword().length <= 255
      );
    }
  }
}
