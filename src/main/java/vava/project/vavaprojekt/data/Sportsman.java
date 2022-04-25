package vava.project.vavaprojekt.data;

import java.net.URL;
import java.util.Locale;
import java.util.Objects;

public final class Sportsman extends User {

    private Integer weight;
    private Integer height;
    private boolean banned;
    private String description;
    private String nick;


    protected Sportsman(String login, String passwordHash, String account_type, String language) {
        super(login, passwordHash, account_type, language);
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

