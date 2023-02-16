package utils;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Equalizer{
    public static boolean isMatch(String cadena, Pattern patron) {
        Matcher mat = patron.matcher(cadena);
        return mat.matches();
    }
}
