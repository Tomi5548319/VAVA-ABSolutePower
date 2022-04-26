package vava.project.vavaprojekt.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import vava.project.vavaprojekt.App;
import vava.project.vavaprojekt.Password;

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
        button_register.setOnAction(this::register);
    }

    private void go_back(ActionEvent event) {
        app.changeWindow("welcome");
    }

    private void register(ActionEvent actionEvent) {
        String login = textField_login.getText();
        String password = passwordField_password.getText();
        String passwordHash = Password.getHash(password);

        if (password.contains("--") || password.contains("'") || password.contains("/*") || password.contains("*/"))
            app.log("Pravdepodobne sa pokusil o SQL injection", 0);

        if(app.getDB().register(login, passwordHash)) {
            // Uspesna registracia -> rovno sa prihlasime
            if(app.login(login, passwordHash)) app.changeWindow("main_view-homepage");
        }
        else {
            System.out.println("Login je obsadeny");
            // TODO popup - nespravny login alebo heslo
        }
    }
}
