package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by Ariel on 6/1/2016.
 */
public class UIMsg extends Application {
    private String msg;
    private static UIMsg instance;


    public UIMsg(String msg) {
        this.msg = msg;
        instance = this;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Resource/Msg.fxml"));
        Pane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Server-Message");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Resource/Icon.png")));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static UIMsg getInstance(){
        return instance;
    }

    public String getMsg(){
        return msg;
    }
}
