package UI;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * This is the controller for the msg UI.
 *
 * @author Ariel
 */
public class MsgController {

    @FXML
    TextArea msg;

    /**
     * Method loads as soon as the FXML file is done loading.
     * <p>
     * The method gets the server message from the UIMsg instance
     * and displays it in the window.
     */
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

    /**
     * Closes the msg window.
     */
    public void close(){
        Stage stage = (Stage) msg.getScene().getWindow();
        stage.close();
    }
}
