package xyz.app.nlf.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import xyz.app.nlf.jpa.entity.Book;
import xyz.app.nlf.jpa.entity.Student;
import xyz.app.nlf.utils.Settable;
import xyz.app.nlf.utils.SharedData;

public class HomeController implements Settable{

    @FXML
    private Label textLabel;
    
    public void initialize() {
        SharedData.get().setSettableController(this);
        String text = String.format("Books: %d\nStudents: %d\nLoans: %d\n", 0,0,0);
        textLabel.setText(text);
    }

    @Override
    public void setBook(Book book) {        
    }

    @Override
    public void setStudent(Student student) {
    }
        
}