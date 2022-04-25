package vava.project.vavaprojekt.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import vava.project.vavaprojekt.App;
import vava.project.vavaprojekt.Password;

public final class LoginController extends Controller {

    @FXML private Text text_username;
    @FXML private TextField textField_username;
    @FXML private Text text_password;
    @FXML private PasswordField passwordField_password;
    @FXML private Button button_login;
    @FXML Button button_back;

    public LoginController(App app) {
        super(app);
    }

    @FXML
    protected void initialize() {
        button_login.setOnAction(this::login);
        button_back.setOnAction(this::go_back);
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

    private void go_back(ActionEvent event) {
        app.changeWindow("welcome");

    }}
