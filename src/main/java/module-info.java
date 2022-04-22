module vava.project.vavaprojekt {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens vava.project.vavaprojekt to javafx.fxml;
    exports vava.project.vavaprojekt;
    exports vava.project.vavaprojekt.controllers;
    opens vava.project.vavaprojekt.controllers to javafx.fxml;
}