
package xyz.app.nlf.utils;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import xyz.app.nlf.jpa.entity.Student;

/**
 *
 * @author Lap
 */
public class StudentCellFactory implements Callback<ListView<Student>, ListCell<Student>> {

    @Override
    public ListCell<Student> call(ListView<Student> param) {
        
        return new ListCell<Student>() {
            
            @Override
            public void updateItem(Student student, boolean emplty) {
                super.updateItem(student, emplty);
                if(emplty || student == null) {
                    setText(null);
                } else {
                    setText(student.toCellString());
                }
            }
        };
    }

}
