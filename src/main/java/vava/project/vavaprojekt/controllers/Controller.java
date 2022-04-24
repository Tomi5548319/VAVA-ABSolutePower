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

    public final void updateLanguage(String fxmlFile, String lang) {
        if (this.app.getUser() == null) return;

        this.app.getUser().setLanguage(lang);
        this.app.changeWindow(fxmlFile);
    }

    //protected abstract void setLanguage(Locale language);
    
}
