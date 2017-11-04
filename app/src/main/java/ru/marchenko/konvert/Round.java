package ru.marchenko.konvert;

import java.util.Locale;

public class Round {
    public static String round(double x) {
        return String.format(Locale.getDefault(),"%.6f", x)
                .replaceAll("0+$","")
                .replaceAll(",$","");
    }
}
