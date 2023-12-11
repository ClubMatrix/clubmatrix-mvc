package com.app.clubmatrix.gui.components;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateInput extends JFormattedTextField {

    public DateInput(int columns) {
        super(getMask());


        setColumns(columns);
        setInputVerifier(new DateVerifier());
    }

    public static class DateVerifier extends InputVerifier {
        @Override
        public boolean verify(JComponent input) {
            return ((JTextField) input).getText().matches("\\d{2}/\\d{2}/\\d{4}");
        }
    }

    static private MaskFormatter getMask() {
        try {
            return new MaskFormatter("##/##/####");
        } catch (ParseException e) {
            System.err.println("Error creating date mask: " + e.getMessage());
            return null;
        }
    }

    public void setDate(Date dateOfBirth) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        try {
            setText(formatter.format(getDate()));
        } catch (Exception e) {
            setText("");
        }
    }

    public Date getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        try {
            return formatter.parse(getText());
        } catch (ParseException e) {
            return null;
        }
    }
}