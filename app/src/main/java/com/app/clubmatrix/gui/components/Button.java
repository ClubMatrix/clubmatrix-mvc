package com.app.clubmatrix.gui.components;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Button extends JButton {

    public Button(String text, ActionListener actionListener) {
        super(text);

        addActionListener(actionListener);
    }
}
