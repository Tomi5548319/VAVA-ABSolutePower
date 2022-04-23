package vava.project.vavaprojekt.data;

import java.util.Locale;

public class Verifier extends User {

    protected Verifier(String login, String passwordHash, Locale language) {
        super(login, passwordHash, "verifier", language);
    }
}
