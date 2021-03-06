package vava.project.vavaprojekt.data;

import vava.project.vavaprojekt.Database;

import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class Sportsman extends User {

    private Integer id;
    private String nick;
    private int avatar_id;
    private String description;
    private double weight;
    private double height;
    private boolean banned;
    private ArrayList<Trainer> myTrainers;

    protected Sportsman(Database db, String id, String login, String passwordHash, String account_type, String language, String nickname,
                        int avatar_id, String description, double weight, double height) {
        super(db, login, passwordHash, account_type, language);

        this.id = Integer.parseInt(id);
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

