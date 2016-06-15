package UI;

import Client.FTPAccessClient;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


/**
 * This is the controller for the main UI.
 *
 * @author Ariel
 */
public class MainController {
    private FTPAccessClient ftpAccessClient;
    private String currentDir = "";

    @FXML
    ListView<String> list;

    @FXML
    Text console;

    /**
     * Exits the program.
     */
    public void exit(){
        System.exit(0);
    }

    /**
     * Opens the about window.
     */
    public void about(){
        UIAbout uiAbout = new UIAbout();
        try {
            uiAbout.start(new Stage());
        } catch (Exception ignored) {}
    }

    /**
     * Method loads as soon as the FXML file is done loading.
     * <p>
     * The method gets the FTPAccesClient object from the UIMain instance,
     * gets all files and directories, pastes them into the ListView and creates the server-msg
     * pop-up.
     */
    @FXML
    protected void initialize(){
        ftpAccessClient = UIMain.getInstance().getFtpAccessClient();
        list.setItems(FXCollections.observableList(ftpAccessClient.getFS()));
        list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (isDir(newValue)){
                ArrayList<String> filesFromDir = ftpAccessClient.getFSFromDir(currentDir + newValue);
                currentDir = currentDir + newValue;
                System.out.println(currentDir);
                System.out.println(filesFromDir);
                filesFromDir.add("---");
                list.setItems(FXCollections.observableList(filesFromDir));
                list.getSelectionModel().select(list.getItems().size()-1);
                list.getFocusModel().focus(list.getItems().size()-1);
                list.scrollTo(0);
            }
            if (Objects.equals(newValue, "..")){
                String[] dirs = currentDir.split("/");
                currentDir = "";
                for (int i = 0; i < dirs.length-1; i++){
                    currentDir = currentDir + dirs[i] + "/";
                }
                System.out.println(currentDir);
                if (Objects.equals(currentDir, "")){
                    init();
                }else {
                    ArrayList<String> filesFromDir = ftpAccessClient.getFSFromDir(currentDir);
                    System.out.println(filesFromDir);
                    filesFromDir.add("---");
                    list.setItems(FXCollections.observableList(filesFromDir));
                    list.getSelectionModel().select(list.getItems().size()-1);
                    list.getFocusModel().focus(list.getItems().size()-1);
                    list.scrollTo(0);
                }
            }
        });

        console.setText(UIMain.getInstance().getMsg()+ "\t");
        System.out.println(list.getItems().toString());

        UIMsg uiMsg = new UIMsg(ftpAccessClient.msg);
        try {
            uiMsg.start(new Stage());
        } catch (Exception ignored) {
        }
    }

    /**
     * Method reloads all files in the working directory.
     */
    public void init(){
        ftpAccessClient = UIMain.getInstance().getFtpAccessClient();
        list.setItems(FXCollections.observableList(ftpAccessClient.getFS()));
        System.out.println(list.getItems().toString());
    }

    /**
     * Method creates a Download object (new thread) to download the selected file.
     */
    public void download(){
        String selected;
        try {
            selected = list.getSelectionModel().getSelectedItem();
        }catch (Exception e){return;}

        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(new Stage());

        String path = selectedDirectory.getPath() + "/" + selected;
        path = path.replace("\\", "/");
        Download dl = new Download(currentDir + selected, ftpAccessClient, path, this);
        dl.start();
    }

    /**
     * Method tells the FTPAccesClient to delete the selected file.
     */
    public void delete(){
        String selected;
        try {
            selected = list.getSelectionModel().getSelectedItem();
        }catch (Exception e){return;}

        boolean deleted = ftpAccessClient.delete(selected);
        if (deleted) {
            try {
                if(ftpAccessClient.fileExists(selected)){
                    console.setText("Could not delete the file.");
                    return;
                }
            } catch (IOException ignored) {
            }
            console.setText("The file was deleted successfully.");
            init();
        } else {
            console.setText("Could not delete the file.");
        }
    }

    /**
     * Method creates a Upload object (new thread) to upload the file which was
     * selected through the FileChooser.
     */
    public void upload(){
        FileChooser directoryChooser = new FileChooser();
        File file = directoryChooser.showOpenDialog(new Stage());

        String path = file.getAbsolutePath().replace("\\", "/");

        Upload upload = new Upload(ftpAccessClient, path, "/" + currentDir,file, this);
        upload.start();
    }

    /**
     * Goes back to the UILogin window. Closes main ui.
     */
    public void back(){
        UILogin uiLogin = new UILogin(ftpAccessClient.getSERVER_ADDRESS());
        Stage stage = (Stage) console.getScene().getWindow();
        stage.close();
        try {
            uiLogin.start(new Stage());
        } catch (Exception ignored) {
        }
    }

    /**
     * Checks if the selected ListView item is a directory (if the item has a "/" at the end).
     * Returns true if the selected item is a directory, false if not.
     *
     * @param name Name of the selected item in the ListView
     * @return boolean
     */
    private boolean isDir(String name){
        return Objects.equals(name.substring(name.length() - 1), "/");
    }
}
