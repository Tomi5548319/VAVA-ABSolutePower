package vava.project.vavaprojekt.data;

import vava.project.vavaprojekt.App;

import java.util.Locale;

public abstract class User {

    private String login;
    private String password_hash;
    private String account_type;
    private String language;

    protected User(String login, String passwordHash, String account_type, String language)
    {
        this.login = login;
        this.password_hash = passwordHash;
        this.account_type = account_type;
        this.language = language;
    }

    public static User login(String login, String passwordHash, String account_type, String language) {

        switch (account_type) {
            case "admin":
                return new Admin(login, passwordHash, language);
            case "verifier":
                return new Verifier(login, passwordHash, language);
            default:
                return new Sportsman(login, passwordHash, account_type, language);
        }
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return "lang_" + this.language;
    }

    public String getAccount_type() {
        return this.account_type;
    }

    public String getLogin() {
        return this.login;
    }
}
