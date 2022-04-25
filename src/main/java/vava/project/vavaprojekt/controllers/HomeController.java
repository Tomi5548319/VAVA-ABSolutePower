package vava.project.vavaprojekt.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;
import vava.project.vavaprojekt.App;
import vava.project.vavaprojekt.Main;
import vava.project.vavaprojekt.data.Exercise;
import vava.project.vavaprojekt.data.Workout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController extends Controller {

    @FXML private Button button_add;
    @FXML private Button button_all;
    @FXML private Button button_del;
    @FXML private DatePicker date_picker;
    @FXML private ListView<Workout> list_workout;
    @FXML private Text no_selected;
    @FXML private Text text_workout;
    private static HBox item;
    private ArrayList<Workout> user_workouts;


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

        //this.user_workouts =

        button_add.setOnMouseClicked(this::add_workout);

        date_picker.valueProperty().addListener((ov, oldValue, newValue) ->
        {
            LocalDate picked = date_picker.getValue();
            ObservableList<Workout> temp = FXCollections.observableArrayList();

            for (Workout w : this.user_workouts) if (w.getScheduled_for().toLocalDate().equals(picked)) temp.add(w);

            this.set_List(temp);
        });
    }


    private void add_workout(MouseEvent mouseEvent) {

        app.changeWindow("main_view-create_workout");

    }


    public void set_List(ObservableList<Workout> data)
    {
        list_workout = new ListView<Workout>(data);
        list_workout.setCellFactory(new Callback<ListView<Workout>, ListCell<Workout>>() {
            @Override
            public ListCell<Workout> call(ListView<Workout> listView) {
                return new CustomListCell();
            }
        });
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
                time.setText(String.valueOf(item.getScheduled_for().getHour() + ":" + item.getScheduled_for().getMinute()));
                setGraphic(content);
            }
            else setGraphic(null);
        }

    }





}
