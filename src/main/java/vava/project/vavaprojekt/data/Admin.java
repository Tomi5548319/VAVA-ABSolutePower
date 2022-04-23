package vava.project.vavaprojekt.data;

public final class Admin extends User {
    protected Admin(String login, String passwordHash) {
        super(login, passwordHash, "admin");
    }
}
