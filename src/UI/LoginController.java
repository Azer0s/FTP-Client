package UI;

import Client.FTPAccessClient;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This is the controller for the login UI.
 *
 * @author Ariel
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


    /**
     * Method loads as soon as the FXML file is done loading.
     * <p>
     * The method sets EventHandler for the main GUI components to scan if
     * the user presses the enter key and then log in.
     */
    @FXML
    protected void initialize(){
        String add = UILogin.getInstance().getServer();
        if (!(add == null)){
            address.setText(add);
        }

        loginBTN.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER){
                    login();
                }
            }
        });

        username.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER){
                    login();
                }
            }
        });

        address.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER){
                    login();
                }
            }
        });

        password.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER){
                    login();
                }
            }
        });
    }

    /**
     * Tries to connect and log into the server.
     * <p>
     * If it fails, the method prints a message on the status textfield.
     */
    public void login(){
        if (address != null && !address.getText().equals("") && !address.getText().equals(null)){
            FTPAccessClient ftpAccessClient = new FTPAccessClient(address.getText(), username.getText(), password.getText());
            ftpAccessClient.run();
            if (ftpAccessClient.connect()){
                status.setText("Connection refused!");
                return;
            }else {
                String answer = ftpAccessClient.login();
                if (answer.equals("false")){
                    status.setText("Can´t log in!");
                    return;
                }
                status.setText(ftpAccessClient.loginText(answer));

                Stage stage = (Stage) loginBTN.getScene().getWindow();
                stage.close();
                UIMain uiMain = new UIMain(ftpAccessClient,ftpAccessClient.loginText(answer));
                try {
                    uiMain.start(new Stage());
                } catch (Exception e) {}
            }
        }else {
            status.setText("Invalid input!");
        }
    }

    /**
     * Exits the program.
     */
    public void close(){
        System.exit(0);
    }
}
