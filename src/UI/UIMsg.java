package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Frame which outputs the welcome message from the FTP server
 *
 * @author Ariel
 */
public class UIMsg extends Application {
    private String msg;
    private static UIMsg instance;


    /**
     * Initializes UIMsg GUI
     *
     * @param msg   Welcome message from the server
     */
    public UIMsg(String msg) {
        this.msg = msg;
        instance = this;
    }

    /**
     * Starts the UIMsg GUI
     *
     * @param primaryStage  New stage to place msg-GUI on
     * @throws Exception    If something goes wrong creating the object
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Resource/Msg.fxml"));
        Pane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Server-Message");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Resource/Icon.png")));
        primaryStage.setScene(scene);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Returns this UIMsg object
     * @return this
     */
    public static UIMsg getInstance(){
        return instance;
    }

    /**
     * Returns the welcome message from the server
     * @return String
     */
    public String getMsg(){
        return msg;
    }
}
