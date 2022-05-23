package xyz.app.nlf.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeController {

    @FXML
    private Label textLabel;
    
    public void initialize() {
        textLabel.setText("Books:\nStudents:\nLoans:\n");
    }
        
}