package vava.project.vavaprojekt.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import vava.project.vavaprojekt.App;
import vava.project.vavaprojekt.Language;

import java.util.Locale;

public final class WelcomeController extends Controller {

    @FXML private Button button_register;
    @FXML private Button button_login;
    @FXML private Text text_welcome;

    public WelcomeController(App app) {
        super(app);
    }

    @FXML
    protected void initialize() {
        //this.updateLanguage();

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
