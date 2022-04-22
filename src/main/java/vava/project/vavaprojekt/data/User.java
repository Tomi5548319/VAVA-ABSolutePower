package vava.project.vavaprojekt.data;

import vava.project.vavaprojekt.App;

public class User {

    private String login;
    private String password;
    private String type;

    private User()
    {

    }

    public static User login(App app, String login, String passwordHash) {
        app.login(login, passwordHash);
        return null;
    }
}
