package com.app.clubmatrix.gui.components;

import java.text.ParseException;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class PhoneInput extends JFormattedTextField {

  public PhoneInput(int columns) {
    super(getMask());
    setColumns(columns);
    setInputVerifier(new PhoneVerifier());
  }

  public static class PhoneVerifier extends InputVerifier {

    @Override
    public boolean verify(JComponent input) {
      return (
        !((JTextField) input).getText().isEmpty() &&
        ((JTextField) input).getText().length() <= 255
      );
    }
  }

  private static MaskFormatter getMask() {
    try {
      return new MaskFormatter("(##) #####-####");
    } catch (ParseException e) {
      System.err.println("Error creating phone mask: " + e.getMessage());
      return null;
    }
  }
}
