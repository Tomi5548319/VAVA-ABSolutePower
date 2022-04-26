package vava.project.vavaprojekt.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import vava.project.vavaprojekt.App;
import vava.project.vavaprojekt.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public final class MenuController extends Controller {
    //protected Pane view;
    private Text prev;
    private String current_page = null;

    @FXML private ImageView menu_flag;
    @FXML private Slider menu_lang;
    @FXML private GridPane menu_options;
    @FXML private Circle menu_photo;
    @FXML private TextField menu_search;
    @FXML private ImageView menu_searchbutton;
    @FXML private Text menu_text_homepage;
    @FXML private Text menu_text_sportsmen;
    @FXML private Text menu_text_training_req;
    @FXML private Text menu_text_stats;
    @FXML private Text menu_text_settings;
    @FXML private Text menu_text_log_out;
    @FXML private Text menu_username;
    @FXML private AnchorPane screen_pane;


    public MenuController(App a) {
        super(a);
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

        prev = menu_text_homepage;
        menu_text_homepage.setFill(Color.DODGERBLUE);

        menu_text_settings.setOnMouseClicked(this::my_profile);
        menu_text_log_out.setOnMouseClicked(this::logout);

        menu_lang.setOnMouseReleased(this::change_language);

        switch (app.getUser().getAccount_type())
        {
            case "sportsman":
                if (this.current_page == null) current_page = "homepage";

                menu_text_homepage.setOnMouseClicked(this::page_home);
                this.remove_menuROW(new ArrayList<Integer>(List.of(2)));

                break;
            case "trainer":
                if (this.current_page == null) current_page = "homepage";

                menu_text_homepage.setOnMouseClicked(this::page_home);
                //menu_text2.setOnMouseClicked(this::textUI);
                //menu_text3.setOnMouseClicked(this::textUI);
                //menu_text4.setOnMouseClicked(this::textUI);
                //menu_text5.setOnMouseClicked(this::textUI);

                break;
            case "admin":
                //if (this.current_page == null) current_page = "";
                this.remove_menuROW(new ArrayList<Integer>(List.of(1,3)));

                menu_text_training_req.setText(ResourceBundle.getBundle(app.getUser().getLanguage()).getString("upgrade_requests"));
                //TODO upgrade requests screen - menutext3 + menu text 1

                menu_photo.setFill(new ImagePattern(new Image(Main.class.getResourceAsStream("icons/icons8-admin-64.png"))));
                break;


        }
        if (this.current_page != null) this.loadPage(current_page);

    }


    //Button actions
    private void change_language(MouseEvent mouseEvent)
    {
        Integer n = Math.toIntExact(Math.round(menu_lang.getValue()));
        String current_lang = app.getUser().getLanguage();

        if ((n == 0 && current_lang.equals("lang_en")) || (n == 1 && current_lang.equals("lang_sk")))
        {
            app.update_language(n);
            app.changeWindow("main_view-" + this.current_page);
        }
    }

    private void my_profile(MouseEvent e) {
        prev.setFill(Color.BLACK);
        prev = menu_text_settings;
        prev.setFill(Color.DODGERBLUE);

        this.loadPage("profile_own");
    }

    private void logout(MouseEvent e) {
        app.logout();
        app.changeWindow("welcome");
    }

    private void page_home(MouseEvent e) {
        prev.setFill(Color.BLACK);
        prev = menu_text_homepage;
        prev.setFill(Color.DODGERBLUE);

        this.loadPage("homepage");
    }

    private void remove_menuROW(ArrayList<Integer> rows)
    {
        for (Node node : this.menu_options.getChildren())
            if(rows.contains(this.menu_options.getRowIndex(node)))
            {
                node.setVisible(false);
                node.setManaged(false);
                menu_options.getRowConstraints().get(this.menu_options.getRowIndex(node)).setMaxHeight(0);
                menu_options.getRowConstraints().get(this.menu_options.getRowIndex(node)).setMinHeight(0);
                //node.setP
                //node.setMa
            }
    }

    //page loader
    public void loadPage(String pagename)
    {
        String lang = app.getUser().getLanguage();
        this.current_page = pagename;

        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/" + pagename + ".fxml"));
            fxmlLoader.setResources(ResourceBundle.getBundle(lang));

            switch (pagename)
            {
                case "homepage":
                    //fxmlLoader.setController(new HomeController(this.app));
                    break;
                case "aplication_for_trainer":
                    //fxmlLoader.setController(new AplicationForTrainerController(this));
                    break;
                case "request_for_training":
                    //fxmlLoader.setController(new RequestForTrainingController(this));
                    break;
                case "profile_own":
                    fxmlLoader.setController(new OwnProfileController(this.app, this));
                    break;

                default:
                    throw new Exception("Zle meno stranky!");
            }

            Pane view = fxmlLoader.load();
            screen_pane.getChildren().clear();
            screen_pane.getChildren().add(view);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}


