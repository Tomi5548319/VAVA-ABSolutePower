package vava.project.vavaprojekt.data;

import vava.project.vavaprojekt.App;

import java.util.Locale;

public abstract class User {

    protected String login;
    protected String password_hash;
    protected String account_type;
    protected Locale language;

    protected User(String login, String passwordHash, String account_type)
    {
        this.login = login;
        this.password_hash = passwordHash;
        this.account_type = account_type;
    }

    public static User login(String login, String passwordHash, String account_type) {

        switch (account_type) {
            case "admin":
                return new Admin(login, passwordHash);
            case "verifier":
                return new Verifier(login, passwordHash);
            case "sportsman":
            case "trainer":
                return new Sportsman(login, passwordHash, account_type);
        }
        return null;
    }
}
