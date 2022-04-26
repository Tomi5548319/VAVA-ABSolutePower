package vava.project.vavaprojekt;

import vava.project.vavaprojekt.data.User;

import java.sql.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public final class Database {

    private Connection conn;

    public Database() {
        this.conn = this.connect();
    }

    private Connection connect() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://vava-mightygainz.cfjpdf44uln2.us-east-1.rds.amazonaws.com:5432/VAVA_MightyGainz_db", "xoross", "vava.G4inz");
            //System.out.println("Uspesne som sa pripojil k databaze");
        }
        catch (SQLException exSQL) {
            System.out.println("Nepodarilo sa pripojit k databaze: " + exSQL.getMessage());
        }
        return null;
    }

    private ResultSet safeExecuteSQL(String query, Object... variables) {
        if (conn == null) {
            System.out.println("Aplikacia nieje pripojena k databaze, preto nevykonala nasledovne query: " + query);
            return null;
        }

        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            int i = 1;
            for (Object variable : variables) {
                System.out.println("obj[" + i + "] type: " + variable.getClass());

                String cls = variable.getClass().toString();

                if(cls.equals("class java.lang.String"))
                    stmt.setString(i, (String)variable);
                else if(cls.equals("class java.lang.Integer"))
                    stmt.setInt(i, (Integer) variable);
                //stmt.setString(i, variable);
                i++;
            }
            return stmt.executeQuery();

        } catch (SQLException exSQL) {
            System.out.println("Chyba pocas vykonavania query: " + exSQL.getMessage());
        }
        //System.out.println(stmt);

        return null;
    }

    //QUERIES
    public User login(String login, String passwordHash) {

        ResultSet rs = this.safeExecuteSQL(
                "SELECT users.id, account_types.type AS account_type, languages.lang AS language\n" +
                        "FROM users\n" +
                        "JOIN account_types ON account_types.id = users.account_type\n" +
                        "JOIN languages ON users.language_id = languages.id\n" +
                        "WHERE login = ? AND password = ?",
                login, passwordHash
        );

        if (rs == null) return null;

        try {
            if (rs.next()) {
                int id = rs.getInt("id");
                String account_type = rs.getString("account_type");
                String language = rs.getString("language");

                System.out.println("Successfully logged in as \"" + login + "\", Account type: " + account_type);

                switch (account_type) {
                    case "sportsman":
                        ResultSet rs2 = this.safeExecuteSQL(
                                "select * from sportsmen where user_id = ?", id
                        );
                        if (rs2 == null) return null;

                        if (rs2.next()) {
                            String nickname = rs2.getString("nickname");
                            int avatar_id = rs2.getInt("avatar_id");
                            String description = rs2.getString("description");
                            double weight = rs2.getDouble("weight");
                            double height = rs2.getDouble("height");
                            boolean banned = rs2.getBoolean("banned");

                            if (!banned)
                                return User.login(this, login, passwordHash, account_type, language, nickname, avatar_id, description, weight, height);
                            else {
                                // TODO sportovec ma ban
                            }
                        }
                        break;
                    case "trainer":
                        // Sportsman or trainer
                        return User.login(this, login, passwordHash, account_type, language);
                    case "admin":
                        return User.login(this, login, passwordHash, account_type, language);
                }
            }
            else {
                System.out.println("User \"" + login + "\" does not exist with password hash \"" + passwordHash + "\"");
            }
        } catch (SQLException e) {
            System.out.println("Chyba, nenasiel sa riadok: " + e.getMessage());
        }

        return null;
    }

    public boolean update_user(String login, String passwordHash, String columns)
    {
        try
        {
            ResultSet rs = this.safeExecuteSQL(
                "UPDATE users\n" +
                        "SET " + columns + "\n" +
                        "WHERE login = ? AND password = ?\n" +
                        "RETURNING *",
                login, passwordHash);

            if (rs == null) return false;

            return rs.next();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void close()
    {
        try {
            conn.close();
        }
        catch (SQLException e) {
            System.out.println("Nezavrel som spojenie: " + e.getMessage());
            //throw new RuntimeException(e);
        }
    }
}