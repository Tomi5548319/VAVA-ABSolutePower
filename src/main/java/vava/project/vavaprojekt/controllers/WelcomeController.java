package vava.project.vavaprojekt.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import vava.project.vavaprojekt.App;

public class WelcomeController {

    private final App app;

    @FXML private Button button_register;
    @FXML private Button button_login;

    public WelcomeController(App app) {
        this.app = app;
    }

    @FXML
    private void initialize() {
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
