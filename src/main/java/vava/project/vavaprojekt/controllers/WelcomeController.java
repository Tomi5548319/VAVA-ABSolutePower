package vava.project.vavaprojekt.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import vava.project.vavaprojekt.App;

public class WelcomeController extends Controller {


    @FXML private Button button_register;
    @FXML private Button button_login;

    public WelcomeController(App app) {
        super(app);
    }

    @FXML
    protected void initialize() {
        button_register.setOnAction(this::register);
        button_login.setOnAction(this::login);
    }

    private void register(ActionEvent event) {
        app.changeWindow("registration");
    }

    private void login(ActionEvent event) {
        app.changeWindow("login");
    }

}
