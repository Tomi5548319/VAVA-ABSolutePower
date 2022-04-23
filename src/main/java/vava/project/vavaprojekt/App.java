package vava.project.vavaprojekt;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vava.project.vavaprojekt.controllers.*;
import vava.project.vavaprojekt.data.User;

/**
 * Trieda obsahujúca spoločné údaje medzi oknami
 */
public final class App {

    private final Stage stage;
    private Database database;
    private String language;
    private User logged_user;

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

    public boolean login(String login, String passwordHash) {
        return (this.logged_user = database.getUser(login, passwordHash)) != null;
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
}
