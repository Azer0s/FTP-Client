package UI;

import Client.FTPAccessClient;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ariel on 01.06.2016.
 */
public class Download extends Thread {

    private final String FILE;
    private FTPAccessClient ftpAccessClient;
    private final String PATH;
    private MainController mainController;

    public Download(String file, FTPAccessClient ftpAccessClient, String path, MainController mainController){
        this.FILE = file;
        this.ftpAccessClient = ftpAccessClient;
        this.PATH = path;
        this.mainController = mainController;
    }

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
