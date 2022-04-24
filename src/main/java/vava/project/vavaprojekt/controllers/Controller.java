package vava.project.vavaprojekt.controllers;

import javafx.fxml.FXML;
import vava.project.vavaprojekt.App;

import java.util.Locale;

public abstract class Controller {
    protected App app;

    public Controller (App a) {
        this.app = a;
    }

    @FXML
    protected abstract void initialize();

    public final void updateLanguage() {
        this.setLanguage(app.getLanguage());
    }

    protected abstract void setLanguage(Locale language);
    
}
