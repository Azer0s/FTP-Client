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

    public Download(String file, FTPAccessClient ftpAccessClient, String path){
        this.FILE = file;
        this.ftpAccessClient = ftpAccessClient;
        this.PATH = path;
    }

    public void run(){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(PATH);
        } catch (FileNotFoundException e) {
        }

        try {
            ftpAccessClient.getFtp().retrieveFile(FILE, fos);
        } catch (IOException e) {
        }finally {
            try {
                if (fos != null){
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
