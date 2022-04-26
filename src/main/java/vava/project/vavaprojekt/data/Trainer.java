package vava.project.vavaprojekt.data;

import vava.project.vavaprojekt.Database;

public class Trainer extends Sportsman{
    protected Trainer(Database db, String id, String login, String passwordHash, String account_type, String language, String nickname, int avatar_id, String description, double weight, double height) {
        super(db, id, login, passwordHash, account_type, language, nickname, avatar_id, description, weight, height);
    }
}
