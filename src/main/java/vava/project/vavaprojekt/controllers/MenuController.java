package vava.project.vavaprojekt.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import vava.project.vavaprojekt.App;
import vava.project.vavaprojekt.Language;
import vava.project.vavaprojekt.Main;

import java.util.Locale;

public class MenuController extends Controller {
    protected Pane view;

    @FXML private GridPane menu_options;
    @FXML private Circle menu_photo;
    @FXML private TextField menu_search;
    @FXML private Text menu_text1;
    @FXML private Text menu_text2;
    @FXML private Text menu_text3;
    @FXML private Text menu_text4;
    @FXML private Text menu_text5;
    @FXML private Text menu_text6;
    @FXML private Text menu_text7;
    @FXML private Text menu_text8;
    @FXML private Text menu_username;
    @FXML private AnchorPane screen_pane;

    public MenuController(App a) {
        super(a);
        //this.loadPage("homepage");
    }

    @FXML
    private void textEntered(MouseEvent event) {
        Text t = (Text) event.getSource();
        t.setUnderline(true);
    }

    @FXML
    private void textExited(MouseEvent event) {
        Text t = (Text) event.getSource();
        t.setUnderline(false);
    }

    @FXML
    protected void initialize() {

        Locale language = app.getLanguage();

        String acc_type = app.getUser().getAccount_type();
        menu_username.setText(app.getUser().getLogin());
        menu_text1.setText(Language.getWord(language, "homepage"));
        menu_text2.setText(Language.getWord(language, "my_workouts"));

        switch (acc_type) {
            case "admin":
                menu_text2.setVisible(false);
                break;
            case "verifier":
                menu_text2.setVisible(false);
                break;
            case "sportsman":

                break;
            case "trainer":

                break;
        }

        //screen_pane = (AnchorPane) view;
        menu_text1.setStyle("-fx-text-fill: #5263ff;");

        menu_text1.setOnMouseClicked(this::page_home);
        //menu_text2.setOnMouseClicked(this::textUI);
        //menu_text3.setOnMouseClicked(this::textUI);
        //menu_text4.setOnMouseClicked(this::textUI);
        //menu_text5.setOnMouseClicked(this::textUI);
        //menu_text6.setOnMouseClicked(this::textUI);
        //menu_text7.setOnMouseClicked(this::textUI);
        menu_text8.setOnMouseClicked(this::logout);

    }

    private void page_home(MouseEvent e)
    {
        //this.loadPage("homepage");
        menu_text1.setStyle("-fx-text-fill: #5263ff;");
    }

    private void logout(MouseEvent e) {
        app.logout();
        app.changeWindow("welcome");
    }

    public void loadPage(String pagename)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/" + pagename + ".fxml"));

            switch (pagename)
            {
                case "homepage":
                    //fxmlLoader.setController(new WelcomeController(this.app));
                    break;
                case "aplication_for_training":
                    break;
                default:
                    throw new Exception("Zle meno stranky!");
            }
            this.view = fxmlLoader.load();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}


