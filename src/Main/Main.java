package Main;

import UI.*;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class creates a UILoin object and starts the program.
 * In order to start the UILoin object, this class inherits from Application.
 * Stage Primary stage (inherited from Application) is passed to UILoin.
 *
 * @author Ariel
 */
public class Main extends Application{

    /**
     * Main method
     * <p>
     * This method is the head of the program. The input parameter is unused.
     *
     * @param args  Launch parameter. Unused.
     */
    public static void main (String [] args){
        launch(args);
    }

    /**
     * Initializes UILogin and starts it with the primary stage of this object as parameter.
     *
     * @param primaryStage      Primary stage to place login UI on
     * @throws Exception        If something goes wrong creating the UILoin object
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        UILogin uiLogin = new UILogin();
        uiLogin.start(primaryStage);
    }
}
