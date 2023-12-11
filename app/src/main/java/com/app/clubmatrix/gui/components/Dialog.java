package com.app.clubmatrix.gui.components;

import java.awt.*;
import javax.swing.*;

public abstract class Dialog extends JDialog {

  protected JPanel panel;
  protected GridBagConstraints gbc;

  public Dialog(JFrame parent, String title) {
    super(parent, title, true);
    panel = new JPanel(new GridBagLayout());
    gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
  }

  protected void addComponent(JComponent component, int x, int y, int width) {
    gbc.gridx = x;
    gbc.gridy = y;
    gbc.gridwidth = width;
    panel.add(component, gbc);
  }
}
