
package xyz.app.nlf.utils;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import xyz.app.nlf.jpa.entity.Book;

/**
 *
 * @author Lap
 */
public class BookCellFactory implements Callback<ListView<Book>, ListCell<Book>>{

    @Override
    public ListCell<Book> call(ListView<Book> param) {
        
        return new ListCell<Book>() {
            
            @Override
            public void updateItem(Book book, boolean empty) {
                super.updateItem(book, empty);
                if(empty || book == null) {
                    setText(null);
                } else {
                    setText(book.toString());
                }
            }
        };
    }
    
}
