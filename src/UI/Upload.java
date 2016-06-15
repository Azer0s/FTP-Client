package UI;

import Client.FTPAccessClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Thread to upload a file from the computer to the working directory of the server.
 * @author Ariel
 */
public class Upload extends Thread {

    private final String PATH;
    private FTPAccessClient ftpAccessClient;
    private MainController mainController;
    private File store;

    /**
     * Initializes Upload Object.
     * <p>
     * Store has to be a valid {@link File} and has to be accessable to the current user.
     *
     * @param ftpAccessClient   A reference to the FTP client
     * @param path              Absolute path of the file you want to upload
     * @param store             File to upload
     * @param mainController    Reference to mainController/main window
     */
    public Upload(FTPAccessClient ftpAccessClient, String path, File store, MainController mainController){
        this.ftpAccessClient = ftpAccessClient;
        this.PATH = path;
        this.store = store;
        this.mainController = mainController;
    }

    /**
     *
     * Starts the upload in a new thread.
     * <p>
     * Reads and uploads file to working directory on server.
     * If something goes wrong, method prints an error message on the console textfield.
     */
    public void run(){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(PATH);
        } catch (FileNotFoundException e) {
            mainController.console.setText("Upload failed!" + "\t");
        }
        try {
            ftpAccessClient.getFtp().storeFile(store.getName(), fis);
            System.out.println(store.getName());
        } catch (IOException e) {
            mainController.console.setText("Upload failed!" + "\t");
        }finally {
            try {
                if (fis != null){
                    fis.close();
                }
            } catch (IOException e) {
                mainController.console.setText("Upload failed!" + "\t");
            }
        }

        try {
            if (!ftpAccessClient.fileExists(store.getName())){
                mainController.console.setText("Upload failed!" + "\t");
                return;
            }
        } catch (IOException ignored) {
        }

        mainController.console.setText("Upload succesful!" + "\t");
        mainController.init();
    }
}
