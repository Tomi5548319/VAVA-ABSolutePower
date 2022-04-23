package vava.project.vavaprojekt.data;

public class Verifier extends User {

    protected Verifier(String login, String passwordHash) {
        super(login, passwordHash, "verifier");
    }
}
