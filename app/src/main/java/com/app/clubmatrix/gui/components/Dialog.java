package com.app.clubmatrix.gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class Dialog extends JDialog  {

    protected JPanel panel;
    protected GridBagConstraints gbc;

    public BaseDialog(JFrame parent, String title) {
        super(parent, title, true);

        panel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
    }

    protected abstract void initializeComponents();

    protected abstract void setupLayout();

    protected void addComponent(JComponent component, int x, int y, int width) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        panel.add(component, gbc);
    }

    protected JButton createButton(String label, ActionListener actionListener) {
        JButton button = new JButton(label);
        button.addActionListener(actionListener);
        return button;
    }
}
