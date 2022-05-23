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
        changeCenterNode(Views.HOME);
    }

    @FXML
    private void onBook(ActionEvent event) {
        changeCenterNode(Views.BOOK);
    }

    @FXML
    private void onStudent(ActionEvent event) {
        changeCenterNode(Views.STUDENT);
    }

    @FXML
    private void onLoans(ActionEvent event) {
        changeCenterNode(Views.LOAN);
    }
    
    private void changeCenterNode(Views view) {
        borderPane.setCenter(ViewManager.get().loadFXML(view));
    }
    
}
