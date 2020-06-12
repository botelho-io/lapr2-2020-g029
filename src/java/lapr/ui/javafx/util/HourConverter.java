package lapr.ui.javafx.util;

import javafx.util.StringConverter;

public class HourConverter extends StringConverter<Double> {

    public static String to_String(Double aDouble) {
        return String.format("%2dh%2dm", (int)(aDouble/60.0), (int)(aDouble - (((int)(aDouble/60.0)) * 60.0)) );
    }

    @Override public String toString(Double aDouble) {
        return to_String(aDouble);
    }

    public static Double from_String(String s) {
        String[] h_m = s.split("h");
        if(h_m.length != 2)
            throw new IllegalArgumentException("Badly formated string");
        double hrs = 0;
        hrs += Integer.parseInt(h_m[0]) * 60;
        h_m[1] = h_m[1].substring(0, h_m[1].length()-1);
        hrs += ((double)Integer.parseInt(h_m[1]));
        return hrs;
    }

    @Override public Double fromString(String s) {
        return from_String(s);
    }
}
