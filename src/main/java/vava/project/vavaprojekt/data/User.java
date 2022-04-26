package vava.project.vavaprojekt.data;

import vava.project.vavaprojekt.Database;

public abstract class User {

    private Database db;
    private String login;
    private String password_hash;
    private String account_type;
    private String language;

    protected User(Database db, String login, String passwordHash, String account_type, String language)
    {
        this.db = db;
        this.login = login;
        this.password_hash = passwordHash;
        this.account_type = account_type;
        this.language = language;
    }

    public static User login(Database db, String login, String passwordHash, String account_type, String language, Object... extras) {

        switch (account_type) {
            case "admin":
                return new Admin(db, login, passwordHash, language);
            case "verifier":
                return new Verifier(db, login, passwordHash, language);
            default:
                return new Sportsman(db, login, passwordHash, account_type, language, (String)extras[0], (Integer)extras[1],
                        (String)extras[2], (Double)extras[3], (Double)extras[4]);
        }
    }


    public String getLanguage() {
        return "lang_" + this.language;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public String getAccount_type() {
        return this.account_type == null ? "" : this.account_type;
    }

    public boolean setLanguage(String language) {
        this.language = language;
        return true;
        // TODO update v databaze
    }

    public boolean setLogin(String new_login) {
        if (!new_login.equals(login) && db.update_user(login, password_hash, "login = '" + new_login + "'")) {
            login = new_login;
            return true;
        }
        return false;
    }

    public boolean setPassword(String act_pass, String new_pass, String new_pass_check) {
        if (act_pass.equals(password_hash) && new_pass.equals(new_pass_check)) {
            password_hash = new_pass;
            return db.update_user(login, act_pass, "password = '" + new_pass + "'");
        }
        return false;
    }
}
