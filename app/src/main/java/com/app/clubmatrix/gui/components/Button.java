package com.app.clubmatrix.gui.components;

import java.awt.event.ActionListener;
import javax.swing.*;

public class Button extends JButton {

  public Button(String text, ActionListener actionListener) {
    super(text);
    addActionListener(actionListener);
  }
}
