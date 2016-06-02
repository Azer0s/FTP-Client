package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Frame which outputs information about the application
 *
 * @author Ariel
 */
public class UIAbout extends Application {

    /**
     * Starts the UIAbout GUI
     *
     * @param primaryStage  New stage to place about-GUI on
     * @throws Exception    If something goes wrong creating the object
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Resource/About.fxml"));
        Pane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("About");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Resource/Icon.png")));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
