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
 * Created by ariel on 31.05.2016.
 */
public class UIMain extends Application{

    private static UIMain instance;
    private FTPAccessClient ftpAccessClient;
    private String msg;

    public UIMain(FTPAccessClient ftpAccessClient, String msg){
        this.ftpAccessClient = ftpAccessClient;
        instance = this;
        this.msg = msg;
    }

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

    public static UIMain getInstance(){
        return instance;
    }

    public FTPAccessClient getFtpAccessClient(){
        return ftpAccessClient;
    }

    public String getMsg(){
        return msg;
    }
}
