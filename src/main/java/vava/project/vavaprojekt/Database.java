package vava.project.vavaprojekt;

import vava.project.vavaprojekt.data.User;

import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

public final class Database {
    private Connection conn = null;



    public Database()
    {

        /*Locale locale = new Locale("sk", "SK");
        System.out.println("w1_" + locale.toString() + ": " + Language.getWord(locale, "w2"));*/

        try {
            this.conn = DriverManager.getConnection("jdbc:postgresql://vava-mightygainz.cfjpdf44uln2.us-east-1.rds.amazonaws.com:5432/VAVA_MightyGainz_db", "xoross", "vava.G4inz");
            System.out.println("Uspesne som sa pripojil k databaze");
        }
        catch (SQLException exSQL) {
            System.out.println("Nepripojil som sa - " + exSQL.getMessage());
        }
    }

    private ResultSet safeExecuteSQL(String query, String... variables) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(query);

        int i = 1;
        for (String variable : variables) {
            stmt.setString(i, variable);
            i++;
        }

        return stmt.executeQuery();
    }

    public User login(String login, String passwordHash) {

        try (ResultSet rs = this.safeExecuteSQL(
                "SELECT account_types.type AS account_type, languages.lang AS language, languages.cntry AS country\n" +
                        "FROM users\n" +
                        "JOIN account_types ON account_types.id = users.account_type\n" +
                        "JOIN languages ON users.language_id = languages.id\n" +
                        "WHERE login = ? AND password = ?",
                login, passwordHash
        ))
        {
            if (rs.next()) {
                String account_type = rs.getString("account_type");
                Locale language = new Locale(rs.getString("language"), rs.getString("country"));

                System.out.println("Successfully logged in as \"" + login + "\", Account type: " + account_type);

                User user = User.login(login, passwordHash, account_type, language);

                switch (account_type) {
                    case "sportsman":
                    case "trainer":
                        // Sportsman or trainer
                        break;
                    case "admin":
                        break;
                    case "verifier":
                        break;
                }

                return user;
            }
            else {
                System.out.println("User \"" + login + "\" does not exist with password hash \"" + passwordHash + "\"");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
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