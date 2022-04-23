package vava.project.vavaprojekt;

import java.util.Locale;
import java.util.ResourceBundle;

public final class Language {
    public static String getWord(Locale language, String key) {

        try {
            ResourceBundle words = ResourceBundle.getBundle("lang", language);

            return words.getString(key);
        }
        catch(Exception e) {
            System.out.printf("Locale: %s, %s key error%n", language.toString(), key);
        }
        return "";
    }
}
