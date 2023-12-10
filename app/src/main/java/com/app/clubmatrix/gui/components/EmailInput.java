package com.app.clubmatrix.gui.components;

import javax.swing.*;

public class EmailInput extends JTextField {

    public EmailInput(int columns) {
        super(columns);

        setInputVerifier(new EmailVerifier());
    }

    static class EmailVerifier extends InputVerifier {
        @Override
        public boolean verify(JComponent input) {
            return ((JTextField) input).getText().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        }
    }
}
