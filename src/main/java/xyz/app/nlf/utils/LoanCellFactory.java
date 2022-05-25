
package xyz.app.nlf.utils;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import xyz.app.nlf.jpa.entity.Loan;

/**
 *
 * @author Lap
 */
public class LoanCellFactory implements Callback<ListView<Loan>, ListCell<Loan>>{

    @Override
    public ListCell<Loan> call(ListView<Loan> param) {
        return new ListCell<Loan>() {
            
            @Override
            public void updateItem(Loan loan, boolean empty) {
                super.updateItem(loan, empty);
                if(empty || loan == null) {
                    setText(null);
                } else {
                    setText(loan.getBook().toShortString());
                }
            }
        };
    }
    
}
