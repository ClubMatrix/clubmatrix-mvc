package com.app.clubmatrix.gui.components;

import javax.swing.*;

public class NameInput extends JTextField {

    public NameInput(int columns) {
        super(columns);

        setInputVerifier(new NameVerifier());
    }

    static class NameVerifier extends InputVerifier {
        @Override
        public boolean verify(JComponent input) {
            return !((JTextField) input).getText().isEmpty() && ((JTextField) input).getText().length() <= 255;
        }
    }
}