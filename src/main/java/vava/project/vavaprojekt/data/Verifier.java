package vava.project.vavaprojekt.data;

import vava.project.vavaprojekt.Database;

import java.util.Locale;

public class Verifier extends User {

    protected Verifier(Database db, String login, String passwordHash, String language) {
        super(db, login, passwordHash, "verifier", language);
    }
}
