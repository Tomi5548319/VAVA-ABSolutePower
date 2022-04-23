package vava.project.vavaprojekt.data;

import java.net.URL;
import java.util.Locale;

public final class Sportsman extends User {

    private Integer weight;
    private Integer height;
    private URL avatarURL;
    private boolean banned;
    private String description;
    private String nick;


    protected Sportsman(String login, String passwordHash, String account_type, Locale language) {
        super(login, passwordHash, account_type, language);
    }



}

