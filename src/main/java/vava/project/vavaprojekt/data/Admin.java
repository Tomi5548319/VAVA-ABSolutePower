package vava.project.vavaprojekt.data;

import java.util.Locale;

public final class Admin extends User {
    protected Admin(String login, String passwordHash, Locale language) {
        super(login, passwordHash, "admin", language);
    }
}
