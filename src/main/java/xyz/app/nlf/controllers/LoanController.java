package xyz.app.nlf.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.util.StringConverter;
import xyz.app.nlf.jpa.dao.LoanDAO;
import xyz.app.nlf.jpa.entity.Book;
import xyz.app.nlf.jpa.entity.Loan;
import xyz.app.nlf.jpa.entity.Student;
import xyz.app.nlf.utils.Settable;
import xyz.app.nlf.utils.SharedData;

/**
 *
 * @author Lap
 */
public class LoanController implements Settable {

    @FXML
    private Label titleLabel;
    @FXML
    private Label studentLabel;
    @FXML
    private Label bookLabel;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ListView<Loan> loanedBooksListView;

    private Student student;
    private Book book;

    public void initialize() {
        SharedData.get().setSettableController(this);
        titleLabel.setText("Loan.");
        datePicker.setValue(LocalDate.now());
        datePicker.setConverter(new StringConverter<LocalDate>() {
            String pattern = "dd.MM.yyyy";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            {
                datePicker.setPromptText(pattern.toLowerCase());
            }

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
    }

    @Override
    public void setBook(Book book) {
        this.book = book;
        bookLabel.setText(book.toShortString());
    }

    @Override
    public void setStudent(Student student) {
        this.student = student;
        studentLabel.setText(student.toCellString());
    }

    @FXML
    private void onLoanBook(ActionEvent event) {
        if(student == null) {
            SharedData.get().writeMessage("Sudent is not selected.");
            return;
        }
        if(book == null) {
            SharedData.get().writeMessage("Book is not selected.");
            return;
        }
        Loan loan = new Loan(datePicker.getValue(), book, student);
        LoanDAO.get().save(loan);
    }

    @FXML
    private void onReturnBook(ActionEvent event) {
    }
    
}
