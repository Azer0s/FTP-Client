package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Frame to log into the server
 *
 * @author Ariel
 */
public class UILogin extends Application {
    private String server = null;
    private static UILogin instance;

    /**
     * Initializes the UILogin object (only used
     * when using the back button in the main frame).
     *
     * @param server    Name of the server (given from the main frame when returning from it)
     */
    public UILogin (String server){
        this.server = server;
        instance = this;
    }

    /**
     * Initializes the UILogin object (used when
     * creating the object normally)
     */
    public UILogin (){
        instance = this;
    }

    /**
     * Starts the UILogin GUI
     *
     * @param primaryStage  New stage to place logn-GUI on
     * @throws Exception    If something goes wrong creating the object
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = FXMLLoader.load(getClass().getResource("/Resource/Login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("save.me FTP");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Resource/Icon.png")));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public String getServer(){
        return server;
    }

    public static UILogin getInstance(){
        return instance;
    }
}
