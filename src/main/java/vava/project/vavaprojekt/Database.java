package vava.project.vavaprojekt;

import java.sql.*;

public class Database {
    private Connection conn = null;

    public Database()
    {
        try {
            this.conn = DriverManager.getConnection("jdbc:postgresql://vava-mightygainz.cfjpdf44uln2.us-east-1.rds.amazonaws.com:5432/VAVA_MightyGainz_db", "xoross", "vava.G4inz");
        }
        catch (SQLException exSQL) {
            System.out.println("Nepripojil som sa - " + exSQL.getMessage());
        }
    }

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
                System.out.println("Account type: " + account_type);
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