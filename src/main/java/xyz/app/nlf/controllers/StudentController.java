
package xyz.app.nlf.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author Lap
 */
public class StudentController {

    @FXML
    private Label titleLabel;
    
    public void initialize() {
        titleLabel.setText("Student.");
    }
    
}
