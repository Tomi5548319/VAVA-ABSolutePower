package vava.project.vavaprojekt.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import vava.project.vavaprojekt.App;
import vava.project.vavaprojekt.Password;
import vava.project.vavaprojekt.data.User;

public class LoginController extends Controller {

    @FXML private Button button_login;
    @FXML private TextField textField_username;
    @FXML private PasswordField passwordField_password;

    public LoginController(App app) {
        super(app);
    }

    @FXML
    protected void initialize() {
        button_login.setOnAction(this::login);
    }

    private void login(ActionEvent event) {
        String login = textField_username.getText();
        String passwordHash = Password.getHash(passwordField_password.getText());

        System.out.println("Login: " + login);
        System.out.println("Password hash: " + passwordHash);

        User.login(app, login, passwordHash);
    }

}
