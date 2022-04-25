package vava.project.vavaprojekt.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
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

    @FXML private ImageView menu_flag;
    @FXML private Slider menu_lang;
    @FXML private GridPane menu_options;
    @FXML private Circle menu_photo;
    @FXML private TextField menu_search;
    @FXML private ImageView menu_searchbutton;
    @FXML private Text menu_text1;
    @FXML private Text menu_text2;
    @FXML private Text menu_text3;
    @FXML private Text menu_text4;
    @FXML private Text menu_text5;
    @FXML private Text menu_text6;
    @FXML private Text menu_text7;
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

        menu_username.setText(app.getUser().getLogin());

        if (app.getUser().getLanguage().equals("lang_en"))
        {
            menu_lang.setValue(1.0);
            menu_flag.setImage(new Image(Main.class.getResourceAsStream("icons/icons8-great-britain-80.png")));
        }
        else
        {
            menu_lang.setValue(0.0);
            menu_flag.setImage(new Image(Main.class.getResourceAsStream("icons/icons8-slovakia-80.png")));

        }

        prev = menu_text1;
        menu_text1.setFill(Color.DODGERBLUE);

        menu_text6.setOnMouseClicked(this::my_profile);
        menu_text7.setOnMouseClicked(this::logout);

        menu_lang.setOnMouseReleased(this::change_language);

        switch (app.getUser().getAccount_type())
        {
            case "sportsman":

            case "trainer":
                menu_text1.setOnMouseClicked(this::page_home);
                //menu_text2.setOnMouseClicked(this::textUI);
                //menu_text3.setOnMouseClicked(this::textUI);
                //menu_text4.setOnMouseClicked(this::textUI);
                //menu_text5.setOnMouseClicked(this::textUI);
                break;
            case "admin":
                menu_photo.setFill(new ImagePattern(new Image(Main.class.getResourceAsStream("icons/icons8-admin-64.png"))));
                break;
        }


    }



    //Button actions
    private void change_language(MouseEvent mouseEvent)
    {
        Integer n = Math.toIntExact(Math.round(menu_lang.getValue()));
        String current_lang = app.getUser().getLanguage();
        String currentpage = "main_view-";




        if ((n == 0 && current_lang.equals("lang_en")) || (n == 1 && current_lang.equals("lang_sk")))
        {
            app.update_language(n);
            if (n == 0) menu_flag.setImage(new Image(Main.class.getResourceAsStream("icons/icons8-slovakia-80.png")));
            else menu_flag.setImage(new Image(Main.class.getResourceAsStream("icons/icons8-great-britain-80.png")));
        }
    }

    private void my_profile(MouseEvent e) {
        prev.setFill(Color.BLACK);
        prev = menu_text6;
        prev.setFill(Color.DODGERBLUE);

        this.loadPage("profile_own", app.getUser().getLanguage());
    }

    private void logout(MouseEvent e) {
        app.logout();
        app.changeWindow("welcome");
    }

    private void page_home(MouseEvent e) {
        prev.setFill(Color.BLACK);
        prev = menu_text1;
        prev.setFill(Color.DODGERBLUE);

        this.loadPage("homepage", app.getUser().getLanguage());
        //System.out.println(view.get);
    }

    //page loader
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


