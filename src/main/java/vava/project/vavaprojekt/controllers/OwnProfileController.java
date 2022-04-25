package vava.project.vavaprojekt.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import vava.project.vavaprojekt.App;

public class OwnProfileController  extends Controller {
    @FXML private Text username;
    @FXML private Text description_text;
    @FXML private Text login_txt;
    @FXML private Text act_password_txt;
    @FXML private Button send_reply_btn1;


    public OwnProfileController(App a) {
        super(a);
    }

    @Override
    protected void initialize() {

    }
}
