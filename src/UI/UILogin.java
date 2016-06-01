package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by ariel on 31.05.2016.
 */
public class UILogin extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = FXMLLoader.load(getClass().getResource("../Resource/Login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("save.me FTP");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../Resource/Icon.png")));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}