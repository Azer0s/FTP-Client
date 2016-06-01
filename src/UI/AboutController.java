package UI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Created by ariel on 31.05.2016.
 */
public class AboutController {
    @FXML
    Button close;

    public void close (){
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    //TODO Documentation
}
