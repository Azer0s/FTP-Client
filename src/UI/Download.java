package UI;

import Client.FTPAccessClient;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Thread to download a file from the server to a directory on the computer.
 * @author ariel
 */
public class Download extends Thread {

    private final String FILE;
    private final String PATH;
    private FTPAccessClient ftpAccessClient;
    private MainController mainController;

    /**
     *
     * Initializes Download Object.
     * <p>
     * The file argument has to be a file which exists on the server.
     * Path has to be a writeable directory for the current user.
     *
     * @param file              File to download
     * @param ftpAccessClient   A reference to the FTP client
     * @param path              Path where file is saved to
     * @param mainController    Reference to mainController/main window
     */
    public Download(String file, FTPAccessClient ftpAccessClient, String path, MainController mainController){
        this.FILE = file;
        this.ftpAccessClient = ftpAccessClient;
        this.PATH = path;
        this.mainController = mainController;
    }

    /**
     *
     * Starts the download in a new thread.
     * <p>
     * Downloads and saves file to given path.
     * If something goes wrong, method prints an error message on the console textfield.
     */
    public void run(){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(PATH);
        } catch (FileNotFoundException e) {
            mainController.console.setText("Download failed!" + "\t");
        }

        try {
            ftpAccessClient.getFtp().retrieveFile(FILE, fos);
        } catch (IOException e) {
            mainController.console.setText("Download failed!" + "\t");
        }finally {
            try {
                if (fos != null){
                    fos.close();
                }
            } catch (IOException e) {
                mainController.console.setText("Download failed!" + "\t");
            }
        }
        mainController.console.setText("Download succesful!" + "\t");
    }
}
