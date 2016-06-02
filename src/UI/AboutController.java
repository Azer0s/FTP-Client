package UI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * This is the controller for the about UI.
 *
 * @author Ariel
 */
public class AboutController {
    @FXML
    Button close;

    /**
     * Closes the about window.
     */
    public void close (){
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
}
