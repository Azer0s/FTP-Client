package UI;

import Client.FTPAccessClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by ariel on 01.06.2016.
 */
public class Upload extends Thread {

    private FTPAccessClient ftpAccessClient;
    private final String PATH;
    private File store;
    private MainController mainController;

    public Upload(FTPAccessClient ftpAccessClient, String path, File store, MainController mainController){
        this.ftpAccessClient = ftpAccessClient;
        this.PATH = path;
        this.store = store;
        this.mainController = mainController;
    }

    public void run(){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(PATH);
        } catch (FileNotFoundException e) {
            mainController.console.setText("Upload failed!");
        }
        try {
            ftpAccessClient.getFtp().storeFile(store.getName(), fis);
        } catch (IOException e) {
            mainController.console.setText("Upload failed!");
        }finally {
            try {
                if (fis != null){
                    fis.close();
                }
            } catch (IOException e) {
                mainController.console.setText("Upload failed!");
            }
        }
        mainController.console.setText("Upload succesful!");
        mainController.init();
    }
}
