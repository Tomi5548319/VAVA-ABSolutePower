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
import java.util.ResourceBundle;

public final class MenuController extends Controller {
    protected Pane view;
    private Text prev;

    @FXML private GridPane menu_options;
    @FXML private Circle menu_photo;
    @FXML private TextField menu_search;
    @FXML private Text menu_text1;
    @FXML private Text menu_text2;
    @FXML private Text menu_text3;
    @FXML private Text menu_text4;
    @FXML private Text menu_text5;
    @FXML private Text menu_text6;
    @FXML private Text menu_text7;//setings
    @FXML private Text menu_text8;
    @FXML private Text menu_username;
    @FXML private AnchorPane screen_pane;


    public MenuController(App a, String pagename, String language) {
        super(a);
        this.loadPage(pagename, language);
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
        //this.updateLanguage();

        //screen_pane = (AnchorPane) view;
        prev = menu_text1;
        menu_text1.setStyle("-fx-text-fill: DEEPSKYBLUE;");

        menu_text1.setOnMouseClicked(this::page_home);
        //menu_text2.setOnMouseClicked(this::textUI);
        //menu_text3.setOnMouseClicked(this::textUI);
        //menu_text4.setOnMouseClicked(this::textUI);
        //menu_text5.setOnMouseClicked(this::textUI);
        //menu_text6.setOnMouseClicked(this::textUI);
        menu_text7.setOnMouseClicked(this::my_profile);
        menu_text8.setOnMouseClicked(this::logout);


    }

    private void page_home(MouseEvent e)
    {
        prev.setStyle("-fx-text-fill: BLACK;");
        prev = menu_text1;
        menu_text1.setStyle("-fx-text-fill: DEEPSKYBLUE;");

        this.loadPage("homepage", app.getUser().getLanguage());
    }

    private void my_profile(MouseEvent e)
    {
        prev.setStyle("-fx-text-fill: BLACK;");
        prev = menu_username;

        menu_username.setStyle("-fx-text-fill: DEEPSKYBLUE;");

        this.loadPage("profile_own", app.getUser().getLanguage());
    }
    private void logout(MouseEvent e) {
        app.logout();
        app.changeWindow("welcome");
    }

    private void loadPage(String pagename, String lang)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/" + pagename + ".fxml"));
            fxmlLoader.setResources(ResourceBundle.getBundle(lang));

            switch (pagename)
            {
                case "homepage":
                    //fxmlLoader.setController(new WelcomeController(this.app));
                    break;
                case "aplication_for_trainer":
                    //fxmlLoader.setController(new AplicationForTrainerController(this));
                    break;
                case "request_for_training":
                    //fxmlLoader.setController(new RequestForTrainingController(this));
                    break;
                case "profile_own":
                    fxmlLoader.setController(new OwnProfileController(this.app));
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


