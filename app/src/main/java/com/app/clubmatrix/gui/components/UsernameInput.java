package com.app.clubmatrix.gui.components;

import javax.swing.*;

public class UsernameInput extends JTextField {

    public UsernameInput(int columns) {
        super(columns);

        setInputVerifier(new UsernameVerifier());
    }

    static class UsernameVerifier extends InputVerifier {
        @Override
        public boolean verify(JComponent input) {
            return !((JTextField) input).getText().isEmpty() && ((JTextField) input).getText().length() <= 255;
        }
    }
}
