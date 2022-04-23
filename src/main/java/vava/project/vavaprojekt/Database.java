package vava.project.vavaprojekt;

import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class Database {
    private Connection conn = null;


    static String getWord(Locale curLoc, String key) {

        try {
            ResourceBundle words = ResourceBundle.getBundle("lang", curLoc);

            return words.getString(key);
        }
        catch(Exception e) {
            System.out.printf("Locale: %s, %s key error%n", curLoc.toString(), key);
        }
        return "";
    }


    public Database()
    {

        Locale[] locales = {
                new Locale("sk", "SK"),
                Locale.ENGLISH
        };

        System.out.println("w1:");

        for (Locale locale : locales) {
            System.out.println("w1_" + locale.toString() + ": " + getWord(locale, "w2"));
        }


        try {
            this.conn = DriverManager.getConnection("jdbc:postgresql://vava-mightygainz.cfjpdf44uln2.us-east-1.rds.amazonaws.com:5432/VAVA_MightyGainz_db", "xoross", "vava.G4inz");
        }
        catch (SQLException exSQL) {
            System.out.println("Nepripojil som sa - " + exSQL.getMessage());
        }
    }

    private String executeSQL() {
        return "";
    }
    //alt+insert na projekte
    // AKE?
    private String modify(String string) {
        return string;
    }

    public boolean login(String login, String passwordHash) {

        login = modify(login);
        passwordHash = modify(passwordHash);

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(
                    "SELECT account_types.type AS account_type FROM users JOIN account_types ON account_types.id = users.account_type WHERE login = '" + login + "' AND password = '" + passwordHash + "'"
            );
            while (rs.next()) {
                String account_type = rs.getString("account_type");
                //rs.get
                System.out.println("Successfully logged in as " + login + ", Account type: " + account_type);
            }
        } catch (SQLException e) {
            System.out.println("User " + login + " does not exist with password hash " + passwordHash);
        }

        return false;
    }

    public void close()
    {
        try {
            conn.close();
        }
        catch (SQLException e) {
            System.out.println("Neuzavrel som spojenie - " + e.getMessage());
            //throw new RuntimeException(e);
        }
    }
}