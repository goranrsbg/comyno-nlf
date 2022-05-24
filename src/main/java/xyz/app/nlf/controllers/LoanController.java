
package xyz.app.nlf.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import xyz.app.nlf.jpa.entity.Book;
import xyz.app.nlf.jpa.entity.Loan;
import xyz.app.nlf.jpa.entity.Student;
import xyz.app.nlf.utils.Settable;
import xyz.app.nlf.utils.SharedData;

/**
 *
 * @author Lap
 */
public class LoanController implements Settable{

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
    
    public void initialize() {
        SharedData.get().setSettableController(this);
        titleLabel.setText("Loan.");
    }

    @Override
    public void setBook(Book book) {
       
    }

    @Override
    public void setStudent(Student student) {
        
    }

    @FXML
    private void onLoanBook(ActionEvent event) {
    }

    @FXML
    private void onReturnBook(ActionEvent event) {
    }
    
}
