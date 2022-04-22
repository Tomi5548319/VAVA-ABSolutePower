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

    private App(Stage stage) {
        //stage.setOnCloseRequest(e -> this.logout());

        this.stage = stage;

        //if(App.users == null)
        //this.loadData();

        stage.setTitle("Mighty Gainz");
        this.changeWindow("welcome");
    }

    public static void start() {
        Stage stage = new Stage();

        App app = new App(stage);
    }

    ////////////////////////////////////////////////////////////
    ////////////// Methods Called from anywhere ////////////////
    ////////////////////////////////////////////////////////////

    // TODO don't call these from the controller, call them on the user directly
    public void displayMessageToUser(String message) {
        // TODO don't stack popups on each other
        // TODO change style
        // TODO popupy maju suradnice na obrazovke, nie v okne (dokazu ist mimo okna ak pohnes oknom (keby nieco kontaktuj Luba))
        System.out.println("Message: \"" + message + "\"");

        final Popup popup = new Popup();
        Label label = new Label(message);
        popup.getContent().add(label);

        popup.setOnShown(e -> {
                    popup.setX(stage.getX() + stage.getWidth()/2 - popup.getWidth()/2);
                    popup.setY(stage.getY() + stage.getHeight() - popup.getHeight()*2);
                }
        );

        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(e -> popup.hide()); // Lambda

        popup.show(stage);
        delay.play();
    }

    private void log(String message) {
        // TODO logs
        System.out.println("Log: " + message);
    }

    public void changeWindow(String fxmlFile, Object... data) {
        try {
            //this.log("Opening file \"" + fxmlFile + "\"");
            //Parent root = FXMLLoader.load(new File("src/ui/fxml/" + fxmlFile + ".fxml").toURI().toURL());
            FXMLLoader fxmlLoader;
            /*switch (fxmlFile) {
                case "hello-view":
                    fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/hello-view.fxml"));
                    break;
                default:*/
                    fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/" + fxmlFile + ".fxml"));
                    /*break;
            }*/

            //root = FXMLLoader.load(getClass().getResource("/fxml/admin.fxml")); // Old version
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
            }
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            //this.error("Ooops! File not found:\n" + e);
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
