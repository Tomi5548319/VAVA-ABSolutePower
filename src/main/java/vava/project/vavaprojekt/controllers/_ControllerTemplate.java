package vava.project.vavaprojekt.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import vava.project.vavaprojekt.App;

public class _ControllerTemplate {

    private final App app;

    // Important: variable name has to be equal to element id in .fxml file
    @FXML private Button exampleButton;
    @FXML private Label exampleLabel;

    public _ControllerTemplate(App app) {
        this.app = app;
    }

    @FXML
    private void initialize() {
        // Add button clicks and other dynamic behaviour here
        exampleButton.setOnAction(this::exampleButtonAction);
    }

    private void exampleButtonAction(ActionEvent event) {
        // Example: change label text on button click
        exampleLabel.setText("Example text");

        // Example: change window to welcome on button click
        app.changeWindow("welcome"); // Check the App.changeWindow(String fxmlFile) before you use this ;)
    }

    /* Copy this into a new Controller without any elements (Import all necessary classes afterwards)

    private final App app;

    public _ControllerTemplate(App app) {
        this.app = app;
    }

    @FXML
    private void initialize() {
        // Put your button click actions here
    }

    private void exampleButtonAction(ActionEvent event) {
        // TODO do something here
    }

    */


    /* Copy this into a new Controller with buttons (Import all necessary classes afterwards)

    private final App app;

    @FXML private Button exampleButton;
    @FXML private Label exampleLabel;

    public _ControllerTemplate(App app) {
        this.app = app;
    }

    @FXML
    private void initialize() {
        exampleLabel.setText("Example text");
        exampleButton.setOnAction(this::exampleButtonAction);
    }

    private void exampleButtonAction(ActionEvent event) {
        app.changeWindow("welcome");
    }

    */

}
