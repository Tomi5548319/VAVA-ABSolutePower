package vava.project.vavaprojekt;

import vava.project.vavaprojekt.data.User;
import vava.project.vavaprojekt.data.Workout;

import java.sql.*;
import java.util.*;
import java.time.LocalDateTime;

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
                else if(cls.equals("class java.sql.Timestamp"))
                    stmt.setTimestamp(i, (Timestamp) variable);
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
    public void log(String activity, int user_id) {
        System.out.println("Pridavam log: " + activity + ", user: " + user_id);
        Timestamp time = Timestamp.valueOf(LocalDateTime.now());

        this.safeExecuteSQL(
                "INSERT INTO user_activity_logs (activity, time, user_id) VALUES\n" +
                        "(?, ?, ?)", activity, time, user_id);
    }

    public void log(String activity, String login) {
        ResultSet rs = this.safeExecuteSQL(
                "SELECT id\n" +
                        "FROM users\n" +
                        "WHERE login = ?", login);
        if (rs == null) return;

        try {
            rs.next();

            int id = rs.getInt("id");
            log(activity, id);

        } catch (SQLException e) {
            System.out.println("Chyba: " + e.getMessage());
        }
    }

    public boolean register(String login, String passwordHash) {
        if (login.contains("--") || login.contains("'") || login.contains("/*") || login.contains("*/"))
            log("Pravdepodobne sa pokusil o SQL injection", 0);

        ResultSet rs = this.safeExecuteSQL(
            "SELECT login\n" +
                    "FROM users\n" +
                    "WHERE login = ?",
            login);
        if (rs == null) return false;

        try {
            if (rs.next()) return false;

            // Language
            rs = this.safeExecuteSQL(
                    "SELECT id FROM languages\n" +
                            "WHERE lang = 'en'");
            if(rs == null) return false;
            rs.next();

            int lang_id = rs.getInt("id");

            // Account type
            rs = this.safeExecuteSQL(
                    "SELECT id FROM account_types\n" +
                            "WHERE type = 'sportsman'");
            if(rs == null) return false;
            rs.next();

            int acc_type_id = rs.getInt("id");

            // Insert user
            this.safeExecuteSQL(
                    "INSERT INTO users (account_type, language_id, login, password) VALUES\n" +
                            "(?, ?, ?, ?);",
                    acc_type_id, lang_id, login, passwordHash);

            // Get ID of inserted user
            rs = this.safeExecuteSQL(
                    "SELECT id FROM users\n" +
                            "WHERE login = ?", login);
            if(rs == null) return false;
            rs.next();

            int user_id = rs.getInt("id");

            this.safeExecuteSQL(
                    "INSERT INTO sportsmen (avatar_id, banned, user_id) VALUES\n" +
                            "(1, false, ?);",
                    user_id);

            log("Zaregistroval sa s loginom '" + login + "'", user_id);

            return true;

        } catch (SQLException e) {
            System.out.println("Chyba: " + e);
        }

        return false;
    }

    public User login(String login, String passwordHash) {

        if (login.contains("--") || login.contains("'") || login.contains("/*") || login.contains("*/"))
            log("Pravdepodobne sa pokusil o SQL injection", 0);

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
                log("Prihlasil sa", login);


                int id = rs.getInt("id");
                String account_type = rs.getString("account_type");
                String language = rs.getString("language");

                System.out.println("Successfully logged in as \"" + login + "\", Account type: " + account_type);

                switch (account_type) {
                    case "sportsman":
                    case "trainer":
                        ResultSet rs2 = this.safeExecuteSQL(
                                "select * from sportsmen where user_id = ?", id
                        );
                        if (rs2 == null) return null;

                        if (rs2.next()) {
                            String sportsmen_id = rs2.getString("id");
                            String nickname = rs2.getString("nickname");
                            int avatar_id = rs2.getInt("avatar_id");
                            String description = rs2.getString("description");
                            double weight = rs2.getDouble("weight");
                            double height = rs2.getDouble("height");
                            boolean banned = rs2.getBoolean("banned");

                            if (!banned)
                                return User.login(this, sportsmen_id, login, passwordHash, account_type, language, nickname, avatar_id, description, weight, height);
                            else {
                                // TODO sportovec ma ban
                            }
                        }
                        break;
                    case "admin":
                        return User.login(this, null,  login, passwordHash, account_type, language);
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

    public ArrayList<Workout> get_workouts (Integer user_id)
    {
        ArrayList<Workout> list = new ArrayList<Workout>();


        ResultSet rs = this.safeExecuteSQL(
                "SELECT workouts.name as workoutname, workouts.owner_id , workouts.description as workoutdesc,workouts.scheduled_for, exercises_in_workouts.*, exercises.name, exercises.description\n" +
                        "from sportsmen\n" +
                        "join workouts on sportsmen.id = "+ user_id + " AND workouts.owner_id = sportsmen.id\n" +
                        "join exercises_in_workouts on exercises_in_workouts.workout_id = workouts.id\n" +
                        "join exercises on exercises.id = exercises_in_workouts.exercise_id");

        try
        {
            //if (!rs.next()) throw new Exception("No workouts found");


            while(rs.next())
            {
                Workout temp = new Workout(rs.getInt("workout_id"), rs.getString("workoutname"), rs.getInt("owner_id"), rs.getString("workoutdesc"), rs.getTimestamp("scheduled_for"));

                //list.
                //Exercise e = new Exercise(rs.getInt("exercise_id"));

                //temp.add
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean update_user(String login, String passwordHash, String columns)
    {
        log("Zmenil udaje nasledovne: " + columns, login);

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