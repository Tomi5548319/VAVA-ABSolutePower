package vava.project.vavaprojekt.controllers;

import javafx.fxml.FXML;
import vava.project.vavaprojekt.App;

public abstract class Controller {
    protected App app;

    public Controller (App a) {
        this.app = a;
    }

    @FXML
    protected abstract void initialize();
    
}
