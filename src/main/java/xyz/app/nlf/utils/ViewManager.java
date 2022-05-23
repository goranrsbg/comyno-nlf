
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
   
   public static ViewManager get() {
       return INSTANCE;
   }
   
   public Parent loadFXML(String fxml) throws IOException {
        String fxmlFile = String.format("/fxml/%s.fxml", fxml);
        FXMLLoader fxmlLoader = new FXMLLoader(ViewManager.class.getResource(fxmlFile));
        return fxmlLoader.load();
    }  
}
