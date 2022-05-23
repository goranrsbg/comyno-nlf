package xyz.app.nlf.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import xyz.app.nlf.utils.SharedData;
import xyz.app.nlf.utils.ViewManager;
import xyz.app.nlf.utils.Views;

public class PrimaryController {

    @FXML
    private Label messageLabel;
    @FXML
    private BorderPane borderPane;
    
    public void initialize() {
        SharedData.get().setPrimaryController(this);
    }

    @FXML
    private void onHome(ActionEvent event) {
        borderPane.setCenter(ViewManager.get().loadFXML(Views.HOME));
    }

    @FXML
    private void onBook(ActionEvent event) {
    }

    @FXML
    private void onStudent(ActionEvent event) {
    }

    @FXML
    private void onLoans(ActionEvent event) {
    }
    
}
