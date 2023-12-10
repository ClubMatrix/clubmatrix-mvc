package com.app.clubmatrix.gui.components;

import javax.swing.*;

public class AddressInput extends JTextField {

    public AddressInput(int columns) {
        super(columns);

        setInputVerifier(new AddressVerifier());
    }

    static class AddressVerifier extends InputVerifier {
        @Override
        public boolean verify(JComponent input) {
            return !((JTextField) input).getText().isEmpty() && ((JTextField) input).getText().length() <= 255;
        }
    }
}