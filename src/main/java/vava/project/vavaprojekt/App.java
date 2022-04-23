package vava.project.vavaprojekt;

import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import vava.project.vavaprojekt.controllers.*;

import java.io.*;

/**
 * Trieda obsahujúca spoločné údaje medzi oknami
 */
public final class App {

    //private User logged;
    private final Stage stage;
    private Database database;
    private String language;

    private App (Stage stage) {
        //stage.setOnCloseRequest(e -> this.logout());

        this.stage = stage;
        this.database = new Database();

        stage.setOnCloseRequest(e -> this.database.close());

        //if(App.users == null)
        //this.loadData();

        stage.setTitle("Mighty Gainz");
        this.changeWindow("welcome");
    }

    public static void start() {
        Stage stage = new Stage();
        new App(stage);
    }

    public void login(String login, String passwordHash) {
        database.login(login, passwordHash);
        //this.changeWindow("main_view");
    }
    private void log(String message) {
        // TODO logs
        System.out.println("Log: " + message);

    }

    public void changeWindow(String fxmlFile, Object... data) {
        try {
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/" + fxmlFile + ".fxml"));


            System.out.println(fxmlFile);
            System.out.println("res " + fxmlLoader.getResources());
            System.out.println("loc " + fxmlLoader.getLocation());
            switch (fxmlFile) {
                case "welcome":
                    fxmlLoader.setController(new WelcomeController(this));
                    break;
                case "login":
                    fxmlLoader.setController(new LoginController(this));
                    break;
                case "registration":
                    fxmlLoader.setController(new RegisterController(this));
                    break;
                case "main_view":
                    System.out.println("call ");
                    fxmlLoader.setController(new MenuController(this));
                    break;
                default:
                    throw new Exception("Screen not found!");
            }

            System.out.println("here ");

            Scene scene = new Scene(fxmlLoader.load());
            System.out.println("here ");
            stage.setScene(scene);
            System.out.println("here ");
            stage.show();
            System.out.println("here ");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public User login(String login, String password) throws AlreadyLoggedInException {
        if(users != null)
            for (User user : users)
                if (user.login(login, password)) {
                    logged = user;
                    return user;
                }

        return null;
    }*/
/*
    public void logout(ActionEvent... event) {
        if(this.logged != null) {
            this.logged.logout();
            User logged = this.logged;
            this.logged = null;
            this.displayMessageToUser("Logged out successfully");

            if(logged instanceof Customer)
                this.changeWindow("mainMenu");
            else
                this.changeWindow("login");
        }
    }*/

    /*public User getLogged() {
        return this.logged;
    }*/

    /*public int userLogged() {
        if(logged == null)
            return 0;
        if(logged instanceof Admin)
            return 1;
        if(logged instanceof HRManager)
            return 2;
        if(logged instanceof SalesManager)
            return 3;
        if(logged instanceof Specialist)
            return 4;
        if(logged instanceof Stockkeeper)
            return 5;
        if(logged instanceof MaterialSpecialist)
            return 6;
        if(logged instanceof Individual)
            return 7;
        if(logged instanceof Company)
            return 8;
        return 0;
    }*/

    /*public boolean isLoginUnique(String login) {
        this.log("Checking if \"" + login + "\" is a unique login");
        for(User u : App.users)
            if(u.checkLogin(login))
                return false;

        return true;
    }*/
}
