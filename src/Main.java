import UI.*;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by ariel on 31.05.2016.
 */
public class Main extends Application{
    public static void main (String [] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        UILogin uiLogin = new UILogin();
        uiLogin.start(primaryStage);
    }
}
