package vava.project.vavaprojekt.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import vava.project.vavaprojekt.App;
import vava.project.vavaprojekt.Language;

import java.util.Locale;

public final class RegisterController extends Controller {

    @FXML Text text_email;
    @FXML TextField textField_email;
    @FXML Text text_login;
    @FXML TextField textField_login;
    @FXML Text text_password;
    @FXML PasswordField passwordField_password;
    @FXML Button button_register;

    public RegisterController(App app) {
        super(app);
    }

    @FXML
    protected void initialize() {
        //this.updateLanguage();
    }

}
