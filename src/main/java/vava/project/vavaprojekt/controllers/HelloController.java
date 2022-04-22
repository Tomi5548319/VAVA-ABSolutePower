package vava.project.vavaprojekt.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import vava.project.vavaprojekt.App;

public class HelloController {
    private final App app;

    @FXML private Button buttonHello;
    @FXML
    private Label welcomeText;

    public HelloController(App app) {
        this.app = app;
    }

    @FXML
    private void initialize() {
        buttonHello.setOnAction(this::onHelloButtonClick);
    }

    private void onHelloButtonClick(ActionEvent event) {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

}