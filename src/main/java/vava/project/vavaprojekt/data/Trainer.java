package vava.project.vavaprojekt.data;

import vava.project.vavaprojekt.Database;

public final class Trainer extends Sportsman{
    protected Trainer(Database db, String login, String passwordHash, String account_type, String language, String nickname, int avatar_id, String description, double weight, double height) {
        super(db, login, passwordHash, account_type, language, nickname, avatar_id, description, weight, height);
    }
}
