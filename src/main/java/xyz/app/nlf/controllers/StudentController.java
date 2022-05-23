
package xyz.app.nlf.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import xyz.app.nlf.jpa.entity.Book;
import xyz.app.nlf.jpa.entity.Student;
import xyz.app.nlf.utils.Settable;
import xyz.app.nlf.utils.SharedData;

/**
 *
 * @author Lap
 */
public class StudentController implements Settable {

    @FXML
    private Label titleLabel;
    
    public void initialize() {
        SharedData.get().setSettableController(this);
        titleLabel.setText("Student.");
    }

    @Override
    public void setBook(Book book) {
        
    }

    @Override
    public void setStudent(Student student) {
        
    }
    
}
