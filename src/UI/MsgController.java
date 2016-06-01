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
        String[] message = UIMsg.getInstance().getMsg().split("\\n");
        if (message[0].substring(0,3).contains("2")){
            String[] newMessage = new String [message.length];
            for (int i = 0; i < message.length; i++){
                newMessage[i] = message[i].substring(4,message[i].length()-1);
            }

            String arrayAsString = "";

            for (int i = 0; i < newMessage.length; i++){
                arrayAsString = arrayAsString + newMessage[i] + "\n";
            }
            msg.setText(arrayAsString);
        }
        else {
            msg.setText(UIMsg.getInstance().getMsg());
        }
    }

    public void close(){
        Stage stage = (Stage) msg.getScene().getWindow();
        stage.close();
    }
}
