package vava.project.vavaprojekt.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import vava.project.vavaprojekt.App;

import java.util.Locale;

public final class RegisterController extends Controller {

    public RegisterController(App app) {
        super(app);

    }

    @FXML
    protected void initialize() {
        this.updateLanguage();

        
    }

    @Override
    protected void setLanguage(Locale language) {

    }

    private void exampleButtonAction(ActionEvent event) {
        // TODO do something here
    }



}
