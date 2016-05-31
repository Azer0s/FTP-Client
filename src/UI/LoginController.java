package UI;

import Client.FTPAccessClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by ariel on 31.05.2016.
 */
public class LoginController {
    @FXML
    PasswordField password;

    @FXML
    TextField username;

    @FXML
    TextField address;

    @FXML
    Text status;

    @FXML
    Button loginBTN;

    public void login(){
        if (address != null && !address.getText().equals("") && !address.getText().equals(null)){
            FTPAccessClient ftpAccessClient = new FTPAccessClient(address.getText(), username.getText(), password.getText());
            ftpAccessClient.run();
            if (ftpAccessClient.connect()){
                status.setText("Connection refused!");
            }else {
                String answer = ftpAccessClient.login();
                status.setText(ftpAccessClient.loginText(answer));

                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {}


                Stage stage = (Stage) loginBTN.getScene().getWindow();
                stage.close();
                UIMain uiMain = new UIMain(ftpAccessClient);
                try {
                    uiMain.start(new Stage());
                } catch (Exception e) {}
            }
        }else {
            status.setText("Invalid input!");
        }
    }

    public void close(){
        System.exit(0);
    }

}
