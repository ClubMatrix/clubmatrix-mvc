package com.app.clubmatrix.utils;

public class Format {
    public static String formatMoney(Integer amount) {
        return String.format("%d.%02d", amount / 100, amount % 100);
    }
}
