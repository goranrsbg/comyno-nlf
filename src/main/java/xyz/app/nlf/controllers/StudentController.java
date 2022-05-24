
package xyz.app.nlf.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import xyz.app.nlf.jpa.dao.StudentsDAO;
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
    @FXML
    private Label idLabel;
    @FXML
    private TextField nameTextField;
    
    private Student loadedStudent;
    
    public void initialize() {
        SharedData.get().setSettableController(this);
        titleLabel.setText("Student.");
        populateStudent(new Student());
    }

    @Override
    public void setBook(Book book) {
        
    }

    @Override
    public void setStudent(Student student) {
        populateStudent(student);
    }

    @FXML
    private void onSave(ActionEvent event) {
        if(loadedStudent != null && collectBook()) {
            if(loadedStudent.getId() == 0) {
                StudentsDAO.get().save(loadedStudent);
            } else {
                StudentsDAO.get().update(loadedStudent);
            }
            populateStudent(new Student());
        }
    }

    @FXML
    private void onDelete(ActionEvent event) {
        if(loadedStudent != null || loadedStudent.getId() > 0) {
            StudentsDAO.get().delete(loadedStudent);
            populateStudent(new Student());
        }
    }

    @FXML
    private void onNewItem(ActionEvent event) {
        populateStudent(new Student());
    }
    
        
    /**
     * Init fields with the student data.
     * 
     * @param student 
     */
    private void populateStudent(Student student) {
        idLabel.setText(student.getId() + "");
        nameTextField.setText(student.getName());
        loadedStudent = student;
    }
    
    /**
     * Collect data from fields and set in the student.
     * 
     * @return True if data are valid.
     */
    private boolean collectBook() {
        loadedStudent.setName(nameTextField.getText());
        return true;
    }
    
}
