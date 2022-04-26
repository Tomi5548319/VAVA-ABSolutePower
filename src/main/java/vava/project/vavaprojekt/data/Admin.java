package vava.project.vavaprojekt.data;

import vava.project.vavaprojekt.Database;

import java.util.Locale;

public final class Admin extends User {
    protected Admin(Database db, String login, String passwordHash, String language) {
        super(db, login, passwordHash, "admin", language);
    }
}
