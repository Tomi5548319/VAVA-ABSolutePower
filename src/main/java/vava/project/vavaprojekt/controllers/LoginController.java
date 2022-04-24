package vava.project.vavaprojekt.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import vava.project.vavaprojekt.App;
import vava.project.vavaprojekt.Language;
import vava.project.vavaprojekt.Password;
import vava.project.vavaprojekt.data.User;

import java.util.Locale;

public final class LoginController extends Controller {

    @FXML private Text text_username;
    @FXML private TextField textField_username;
    @FXML private Text text_password;
    @FXML private PasswordField passwordField_password;
    @FXML private Button button_login;

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

        if(app.login(login, passwordHash)) app.changeWindow("main_view-homepage");
        else
        {
            //TODO : POP UP message
        }
    }
}
