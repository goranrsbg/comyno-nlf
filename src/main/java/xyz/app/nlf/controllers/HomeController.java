package xyz.app.nlf.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import xyz.app.nlf.jpa.dao.BooksDAO;
import xyz.app.nlf.jpa.dao.StudentsDAO;
import xyz.app.nlf.jpa.entity.Book;
import xyz.app.nlf.jpa.entity.BooksCount;
import xyz.app.nlf.jpa.entity.Student;
import xyz.app.nlf.utils.Settable;
import xyz.app.nlf.utils.SharedData;

public class HomeController implements Settable {

    @FXML
    private Label textLabel;

    public void initialize() {
        SharedData.get().setSettableController(this);
        BooksCount bc = BooksDAO.get().countBooks();
        int totalStudents = StudentsDAO.get().countStudents();
        String text = String.format("Library\n Total Books: %d\nTotal Students: %d\nTotal Book Loaned: %d", bc.getQty(), totalStudents, bc.getQtyLoan());
        textLabel.setText(text);
    }

    @Override
    public void setBook(Book book) {
    }

    @Override
    public void setStudent(Student student) {
    }

}
