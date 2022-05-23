package xyz.app.nlf.utils;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Lap
 */
public class ViewManager {

    private static final ViewManager INSTANCE = new ViewManager();

    private static final Logger LOGGER = LoggerFactory.getLogger(ViewManager.class);

    private BorderPane mainPane;

    private ViewManager() {
    }

    public static ViewManager get() {
        return INSTANCE;
    }

    public BorderPane loadPrimary() {
        mainPane = (BorderPane) loadFXML(Views.PRIMARY);
        return mainPane;
    }

    public Parent loadFXML(Views view) {
        Parent parent = new Label("Error loading view.");
        try {
            String fxmlFile = String.format("/fxml/%s.fxml", view.fileName());
            FXMLLoader fxmlLoader = new FXMLLoader(ViewManager.class.getResource(fxmlFile));
            parent = fxmlLoader.load();
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return parent;
    }
}
