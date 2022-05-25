package xyz.app.nlf.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.util.StringConverter;
import xyz.app.nlf.jpa.dao.LoanDAO;
import xyz.app.nlf.jpa.entity.Book;
import xyz.app.nlf.jpa.entity.Loan;
import xyz.app.nlf.jpa.entity.Student;
import xyz.app.nlf.utils.LoanCellFactory;
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
        // init loaned books list view
        loanedBooksListView.setCellFactory(new LoanCellFactory());
        loanedBooksListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @Override
    public void setBook(Book book) {
        if (book.canLoan()) {
            this.book = book;
            bookLabel.setText(book.toShortString());
        } else {
            SharedData.get().writeMessage(String.format("%s can not be loaned. Left over is %d.", book.toShortString(), book.getQuantityLeftOver()));
        }
    }

    @Override
    public void setStudent(Student student) {
        this.student = student;
        studentLabel.setText(student.toCellString());
        loadStudentsLoans();
    }

    @FXML
    private void onLoanBook(ActionEvent event) {
        if (student == null) {
            SharedData.get().writeMessage("Sudent is not selected.");
            return;
        }
        if (book == null) {
            SharedData.get().writeMessage("Book is not selected.");
            return;
        }
        Loan loan = new Loan(datePicker.getValue(), book, student);
        LoanDAO.get().save(loan);
        // refresh student loan list
        loadStudentsLoans();
        // clear loaded book
        clearLoadedBook();
    }

    @FXML
    private void onReturnBook(ActionEvent event) {
        Loan loan = loanedBooksListView.getSelectionModel().getSelectedItem();
        if (student == null) {
            SharedData.get().writeMessage("Sudent is not selected.");
            return;
        }
        if (loan == null) {
            SharedData.get().writeMessage(String.format("%s loaned book is not chosen.", student));
            return;
        }
        LoanDAO.get().update(loan);
        // refresh loans
        loadStudentsLoans();
    }

    private void loadStudentsLoans() {
        loanedBooksListView.getItems().setAll(LoanDAO.get().readByStudent(student));
    }

    private void clearLoadedBook() {
        book = null;
        bookLabel.setText("");
    }
    
}
