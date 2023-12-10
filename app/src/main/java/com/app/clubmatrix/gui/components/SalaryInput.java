package com.app.clubmatrix.gui.components;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DecimalFormat;
import java.text.ParseException;

public class SalaryInput extends JFormattedTextField {
    private static final DecimalFormat format = new DecimalFormat("#,##0.00");

    public SalaryInput(int columns) {
        super(format);
        setColumns(columns);
        setValue(0.00);

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    String text = getText().replaceAll("\\D", "");
                    if (text.isEmpty()) {
                        setValue(0.00);
                        return;
                    }

                    long value = Long.parseLong(text);
                    if (value <= 0) {
                        setValue(0.00);
                        return;
                    }

                    setText(format.format(value / 100.0));
                } catch (NumberFormatException ex) {
                    setValue(0.00);
                }
            }
        });
    }

    @Override
    public Object getValue() {
        try {
            return format.parseObject(super.getText());
        } catch (ParseException e) {
            return 0.00;
        }
    }
}