package vava.project.vavaprojekt.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import vava.project.vavaprojekt.App;

public final class RegisterController extends Controller {

    @FXML Text text_login;
    @FXML TextField textField_login;
    @FXML Text text_password;
    @FXML PasswordField passwordField_password;
    @FXML Button button_register;
    @FXML Button button_back;

    public RegisterController(App app) {
        super(app);
    }

    @FXML
    protected void initialize() {
        button_back.setOnAction(this::go_back);
    }

    private void go_back(ActionEvent event) {
        app.changeWindow("welcome");
    }
}
