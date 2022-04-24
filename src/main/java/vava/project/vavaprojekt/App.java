package vava.project.vavaprojekt;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vava.project.vavaprojekt.controllers.*;
import vava.project.vavaprojekt.data.User;

import java.util.Locale;
import java.util.ResourceBundle;

public final class App {

    private final Stage stage;
    private Database database;
    private User logged_user = null;
    private static String defaultLanguage = "lang_en";

    private App (Stage stage) {
        stage.setOnCloseRequest(e -> this.database.close());

        this.stage = stage;
        this.database = new Database();

        stage.setOnCloseRequest(e -> this.database.close());

        stage.setTitle("Mighty Gainz");
        this.changeWindow("welcome");
    }

    public User getUser() {
        return this.logged_user;
    }

    public static void start() {
        Stage stage = new Stage();
        new App(stage);
    }

    private void log(String message) {
        // TODO logs
        System.out.println("Log: " + message);
    }

    public void changeWindow(String fxmlFile, Object... data) {

        if (this.logged_user != null) System.out.println("Language: " + this.logged_user.getLanguage());

        String[] parts = fxmlFile.split("-");


        try {
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/" + parts[0] + ".fxml"));

            String resBundle;

            if (this.logged_user == null) resBundle = defaultLanguage;
            else resBundle = this.logged_user.getLanguage();

            fxmlLoader.setResources(ResourceBundle.getBundle(resBundle));


            switch (parts[0]) {
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
                    fxmlLoader.setController(new MenuController(this,parts[1],resBundle ));
                    break;
                default:
                    throw new Exception("Screen not found!");
            }

            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean login(String login, String passwordHash) {
        this.logged_user = database.login(login, passwordHash);
        //this.language = logged_user.getLanguage();

        return this.logged_user != null;
    }

    public void logout() {
        this.logged_user = null;
    }
}
