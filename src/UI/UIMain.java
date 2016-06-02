package UI;

import Client.FTPAccessClient;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Main frame of the program
 *
 * @author Ariel
 */
public class UIMain extends Application{

    private static UIMain instance;
    private FTPAccessClient ftpAccessClient;
    private String msg;

    /**
     * Initializes UIMain GUI
     * @param ftpAccessClient   Reference to the FTP client
     * @param msg               Welcome message from the server
     */
    public UIMain(FTPAccessClient ftpAccessClient, String msg){
        this.ftpAccessClient = ftpAccessClient;
        instance = this;
        this.msg = msg;
    }

    /**
     * Starts the UIMain GUI
     *
     * @param primaryStage  New stage to place main-GUI on
     * @throws Exception    If something goes wrong creating the object
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Resource/Main.fxml"));
        Pane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

            }
        });
        primaryStage.setTitle("save.me FTP");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Resource/Icon.png")));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Returns a UIMain instance
     *
     * @return UIMain
     */
    public static UIMain getInstance(){
        return instance;
    }

    /**
     * Returns the FTP client
     *
     * @return FTPAccessClient
     */
    public FTPAccessClient getFtpAccessClient(){
        return ftpAccessClient;
    }

    /**
     * Returns the welcome message from the server
     * @return String
     */
    public String getMsg(){
        return msg;
    }
}
