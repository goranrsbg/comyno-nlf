
package xyz.app.nlf.utils;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author Lap
 */
public class ViewManager {
    
   private static final ViewManager INSTANCE = new ViewManager();
   
   private ViewManager() {
   
   }
   
   public static ViewManager get() {
       return INSTANCE;
   }
   
   public Parent loadPrimaryFXML() throws IOException {
        String fxmlFile = String.format("/fxml/%s.fxml", Views.PRIMARY.fileName());
        FXMLLoader fxmlLoader = new FXMLLoader(ViewManager.class.getResource(fxmlFile));
        return fxmlLoader.load();
    }  
}
