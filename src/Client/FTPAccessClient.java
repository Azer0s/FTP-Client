package Client;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by ariel on 31.05.2016.
 */
public class FTPAccessClient extends Thread{

    private FTPClient ftp;
    private FTPClientConfig config;
    private final String SERVER_ADDRESS;
    private final String USERNAME;
    private final String PASSWORD;

    public FTPAccessClient(String serverAddress, String username, String password){
        ftp = new FTPClient();
        config = new FTPClientConfig();
        ftp.configure(config);

        this.SERVER_ADDRESS = serverAddress;
        this.USERNAME = username;
        this.PASSWORD = password;
    }

    public boolean connect(){
        boolean error = false;

        try {
            int reply;
            ftp.connect(SERVER_ADDRESS);
            System.out.println("Connected to " + SERVER_ADDRESS + "!");
            System.out.println(ftp.getReplyString());

            reply = ftp.getReplyCode();

            if(!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                System.err.println("FTP server refused connection.");
                System.exit(1);
            }
        }catch (Exception e){
            error = true;
        }
        return error;
    }

    public String login(){
        if (!PASSWORD.equals("") && !USERNAME.equals("")){
            boolean succes = false;
            try {
                succes = ftp.login(USERNAME, PASSWORD);
            } catch (IOException e) {}
            if (succes){
                return "true";
            }
        }

        if (USERNAME.equals("")){
            return "na";
        }

        try {
            ftp.logout();
        } catch (IOException e) {}
        return "false";
    }

    public String loginText(String text){
        switch (text){
            case "true":
                return "Logged in!";
            case "false":
                return "Can´t log in!";
            case "na":
                return "Not logged in!";
        }
        return null;
    }

    public FTPFile[] getFTPFile(){
        try {
            return ftp.listFiles();
        } catch (IOException e) {
        }
        return new FTPFile[0];
    }

    public FTPFile[] getFilesFromDirectory(String dir){
        try {
            return ftp.listFiles(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new FTPFile[0];
    }

    public ArrayList<String> getFS(){
        ArrayList<String> filesAsString = new ArrayList<>();

        FTPFile[] files = getFTPFile();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (FTPFile file: files) {
            String details = file.getName();
            if (!file.isDirectory()){
                filesAsString.add(details);
            }
        }

        return filesAsString;
    }

    public boolean delete(String fileToDelete) {
        try {
            ftp.deleteFile(fileToDelete);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public FTPClient getFtp() {
        return ftp;
    }
}
