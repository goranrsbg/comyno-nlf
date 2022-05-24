
package xyz.app.nlf.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import xyz.app.nlf.jpa.dao.BooksDAO;
import xyz.app.nlf.jpa.entity.Book;
import xyz.app.nlf.jpa.entity.Student;
import xyz.app.nlf.utils.Settable;
import xyz.app.nlf.utils.SharedData;

/**
 *
 * @author Lap
 */
public class BookController implements Settable{

    @FXML
    private Label titleLabel;
    @FXML
    private Label idLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField quantityTextField;
    
    private Book loadedBook;
    
    public void initialize() {
        SharedData.get().setSettableController(this);
        titleLabel.setText("Book.");
        populateBook(new Book());
    }

    @Override
    public void setBook(Book book) {
        populateBook(book);
    }

    @Override
    public void setStudent(Student student) {
        
    }

    @FXML
    private void onSave(ActionEvent event) {
        if(loadedBook != null && collectBook()) {
            if(loadedBook.getId() == 0) {
                BooksDAO.get().save(loadedBook);
            } else {
                BooksDAO.get().update(loadedBook);
            }
            populateBook(new Book());
        }
    }

    @FXML
    private void onDelete(ActionEvent event) {
        if(loadedBook != null || loadedBook.getId() > 0) {
            BooksDAO.get().delete(loadedBook);
            populateBook(new Book());
        }
    }

    @FXML
    private void onNewItem(ActionEvent event) {
        populateBook(new Book());
    }
    
    /**
     * Init fields with the book data.
     * 
     * @param book 
     */
    private void populateBook(Book book) {
        idLabel.setText(book.getId() + "");
        nameTextField.setText(book.getName());
        quantityTextField.setText(book.getQuantity() + "");
        loadedBook = book;
    }
    
    /**
     * Collect data from fields and set in the book.
     * 
     * @return True if data are valid.
     */
    private boolean collectBook() {
        String qtyText = quantityTextField.getText();
        try {
            int qty = Integer.parseInt(qtyText);
            loadedBook.setQuantity(qty);
            loadedBook.setName(nameTextField.getText());
            return true;
        } catch (NumberFormatException e) {
            SharedData.get().getPrimaryController().setMessageText(qtyText + " is not valid number.");
        }
        return false;
    }
    
}
