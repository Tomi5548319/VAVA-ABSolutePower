package vava.project.vavaprojekt.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import vava.project.vavaprojekt.App;
import vava.project.vavaprojekt.Password;
import vava.project.vavaprojekt.data.Sportsman;

import java.util.Objects;

public final class OwnProfileController  extends Controller {
    @FXML private Text text_nickname_heading;
    @FXML private TextArea textArea_description;
    @FXML private Text text_nickname_change;
    @FXML private Text text_login;
    @FXML private Text text_act_password;
    @FXML private Text text_new_password;
    @FXML private Text text_confirm_password;
    @FXML private TextField textField_nickname;
    @FXML private TextField textField_login;
    @FXML private PasswordField passField_act_password;
    @FXML private PasswordField passField_new_password;
    @FXML private PasswordField passField_confirm_password;
    @FXML private Button btn_save_changes;
    @FXML private Button btn_upgrade;

    public OwnProfileController(App a) {
        super(a);
    }

    @Override
    protected void initialize() {
        if (app.getUser() instanceof Sportsman) {
            text_nickname_heading.setText(((Sportsman)app.getUser()).getNickname());
            textArea_description.setText(((Sportsman)app.getUser()).getDescription());
            textField_nickname.setText(((Sportsman)app.getUser()).getNickname());
        }
        else {
            text_nickname_heading.setText(app.getUser().getLogin());
            textArea_description.setVisible(false);
            textField_nickname.setVisible(false);
            text_nickname_change.setVisible(false);
            btn_upgrade.setVisible(false);
        }
        textField_login.setText((app.getUser()).getLogin());

        btn_save_changes.setOnMouseClicked(this::saveChanges);
    }

    private void saveChanges(MouseEvent mouseEvent) {
        // TODO update v sportovcovi (ktory da update na databazu)
        // TODO pri update hesla checkovat ci je stare heslo spravne a ci su obe nove hesla rovnake
        String new_desc = textArea_description.getText();
        String new_nick = textField_nickname.getText();
        String new_login = textField_login.getText();

        String act_pass = Password.getHash(passField_act_password.getText());
        String new_pass = Password.getHash(passField_new_password.getText());
        String new_pass_check = Password.getHash(passField_confirm_password.getText());

        // TODO popup ktory informuje pouzivatela ci sa podarilo zmenit jeho udaje
        if (app.getUser() instanceof Sportsman) {
            ((Sportsman)app.getUser()).updateDescription(new_desc);
            ((Sportsman)app.getUser()).updateNickname(new_nick);
        }

        if (app.getUser().setLogin(new_login))
            System.out.println("Login bol zmeneny uspesne");
        else {
            textField_login.setText(app.getUser().getLogin());
            System.out.println("Login sa nepodarilo zmenit");
        }
        if (app.getUser().setPassword(act_pass, new_pass, new_pass_check)) {
            passField_act_password.setText("");
            passField_new_password.setText("");
            passField_confirm_password.setText("");
            System.out.println("Heslo bolo zmenene uspesne");
        }
        else
            System.out.println("Heslo sa nepodarilo zmenit");
    }
}
