package xyz.app.nlf.controllers;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import xyz.app.nlf.jpa.dao.BooksDAO;
import xyz.app.nlf.jpa.dao.StudentsDAO;
import xyz.app.nlf.jpa.entity.Book;
import xyz.app.nlf.jpa.entity.Student;
import xyz.app.nlf.utils.BookCellFactory;
import xyz.app.nlf.utils.Settable;
import xyz.app.nlf.utils.SharedData;
import xyz.app.nlf.utils.StudentCellFactory;
import xyz.app.nlf.utils.ViewManager;
import xyz.app.nlf.utils.Views;

public class PrimaryController {

    @FXML
    private Label messageLabel;
    @FXML
    private BorderPane borderPane;
    @FXML
    private ListView<Book> booksListView;
    @FXML
    private ListView<Student> studentsListView;

    public void initialize() {
        SharedData.get().setPrimaryController(this);
        booksListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        booksListView.setCellFactory(new BookCellFactory());
        studentsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        studentsListView.setCellFactory(new StudentCellFactory());
    }

    @FXML
    private void onHome(ActionEvent event) {
        changeCenterNode(Views.HOME);
    }

    @FXML
    private void onBook(ActionEvent event) {
        changeCenterNode(Views.BOOK);
    }

    @FXML
    private void onStudent(ActionEvent event) {
        changeCenterNode(Views.STUDENT);
    }

    @FXML
    private void onLoans(ActionEvent event) {
        changeCenterNode(Views.LOAN);
    }

    @FXML
    private void onRefreshBooks(ActionEvent event) {
        refreshBooks();
    }

    @FXML
    private void onRefreshStudents(ActionEvent event) {
        List<Student> students = StudentsDAO.get().readAll();
        studentsListView.getItems().clear();
        studentsListView.getItems().addAll(students);
    }

    @FXML
    private void onBookListKeyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            transferSelectedBook();
        }
    }

    @FXML
    private void onBookListMouseClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            transferSelectedBook();
        }
    }

    @FXML
    private void onStudentListMouseClicked(MouseEvent event) {
        if(event.getClickCount() == 2) {
            transferSelectedStudent();
        }
    }

    @FXML
    private void onStudentListKeyReleased(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            transferSelectedStudent();
        }
    }

    private void changeCenterNode(Views view) {
        borderPane.setCenter(ViewManager.get().loadFXML(view));
    }

    public void setMessageText(String text) {
        messageLabel.setText(text);
    }

    private void transferSelectedBook() {
        Book book = booksListView.getSelectionModel().getSelectedItem();
        Settable controller = SharedData.get().getSettableController();
        if (book != null && controller != null) {
            controller.setBook(book);
        }
    }

    private void transferSelectedStudent() {
        Student student = studentsListView.getSelectionModel().getSelectedItem();
        Settable controller = SharedData.get().getSettableController();
        if (student != null && controller != null) {
            controller.setStudent(student);
        }
    }
    
    /**
     * Refresh items in book list view.
     */
    public void refreshBooks() {
        List<Book> books = BooksDAO.get().readAll();
        booksListView.getItems().clear();
        booksListView.getItems().addAll(books);
    }

}
