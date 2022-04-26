package vava.project.vavaprojekt.data;

import vava.project.vavaprojekt.Database;

import java.net.URL;
import java.util.Locale;
import java.util.Objects;

public final class Sportsman extends User {
    private String nick;
    private int avatar_id;
    private String description;
    private double weight;
    private double height;
    private boolean banned;

    protected Sportsman(Database db, String login, String passwordHash, String account_type, String language, String nickname,
                        int avatar_id, String description, double weight, double height) {
        super(db, login, passwordHash, account_type, language);

        this.nick = nickname;
        this.avatar_id = avatar_id;
        this.description = description;
        this.weight = weight;
        this.height = height;
    }

    public String getNickname() {
        return this.nick == null ? "" : this.nick;
    }

    public String getDescription() {
        return this.description == null ? "" : this.description;
    }


    public void updateNickname(String new_nick) {
        if (!new_nick.equals(nick)) {
            nick = new_nick;
            // TODO update v databaze
        }
    }

    public void updateDescription(String new_description) {
        if (!new_description.equals(description)) {
            description = new_description;
            // TODO update v databaze
        }
    }
}

