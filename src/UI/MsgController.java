package UI;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Created by Ariel on 6/1/2016.
 */
public class MsgController {

    @FXML
    TextArea msg;

    @FXML
    protected void initialize(){
        msg.setText(UIMsg.getInstance().getMsg());
    }

    public void close(){
        Stage stage = (Stage) msg.getScene().getWindow();
        stage.close();
    }
}
