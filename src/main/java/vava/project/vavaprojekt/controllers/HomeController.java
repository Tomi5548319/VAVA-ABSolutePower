package vava.project.vavaprojekt.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import vava.project.vavaprojekt.App;
import vava.project.vavaprojekt.Main;
import vava.project.vavaprojekt.data.Workout;

import java.util.ResourceBundle;

public class HomeController extends Controller {

    @FXML private Button button_add;
    @FXML private Button button_all;
    @FXML private Button button_del;
    @FXML private DatePicker date_picker;
    @FXML private ListView<?> list_workout;
    @FXML private Text no_selected;
    @FXML private Text text_workout;
    private static HBox item;

    public HomeController(App a) {
        super(a);
    }

    @Override
    protected void initialize() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/item_calendar.fxml"));
            this.item = fxmlLoader.load();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //item.lookup()
        //list_workout.setCellFactory();
    }

    private class CustomListCell extends ListCell<Workout> {
        private HBox content;
        private Text name;
        private ProgressBar pr;
        private Text time;

        public CustomListCell() {
            super();
            content = new HBox(item);
            name = (Text) content.lookup("#item_time");
            pr = (ProgressBar) content.lookup("#item_progress");
            time = (Text) content.lookup("#item_name");
        }

        @Override
        protected void updateItem(Workout item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null && !empty)
            {
                name.setText(item.getName());
                pr.setProgress(item.getProgress());
                //time.setText(item.getTime());
                setGraphic(content);
            }
            else setGraphic(null);
        }
    }


    public static class HBoxCell extends HBox
    {
        //item.ge
        Text time = (Text) item.lookup("#item_time");
        ProgressBar pr = (ProgressBar) item.lookup("#item_progress");
        Text name = (Text) item.lookup("#item_name");

        //item.getXhlid


                /*
        HBoxCell(String nameText, String timeText, double progress) {
            super();

            time.setText();
            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            button.setText(buttonText);

            this.getChildren().addAll(label, button);
        }
        */

    }





}
