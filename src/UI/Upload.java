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

    public Upload(FTPAccessClient ftpAccessClient, String path, File store){
        this.ftpAccessClient = ftpAccessClient;
        this.PATH = path;
        this.store = store;
    }

    public void run(){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(PATH);
        } catch (FileNotFoundException e) {
        }
        try {
            ftpAccessClient.getFtp().storeFile(store.getName(), fis);
        } catch (IOException e) {
        }finally {
            try {
                if (fis != null){
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
