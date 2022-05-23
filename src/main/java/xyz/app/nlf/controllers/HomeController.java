package xyz.app.nlf.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeController {

    @FXML
    private Label textLabel;
    
    public void initialize() {
        String text = String.format("Books: %d\nStudents: %d\nLoans: %d\n", 0,0,0);
        textLabel.setText(text);
    }
        
}