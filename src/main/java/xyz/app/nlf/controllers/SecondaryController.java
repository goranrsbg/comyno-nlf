package xyz.app.nlf.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import xyz.app.nlf.App;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}