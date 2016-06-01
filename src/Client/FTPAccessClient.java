package Client;

import org.apache.commons.net.ftp.*;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Class which connects to the FTP server via the apache commons.
 * @author ariel
 */
public class FTPAccessClient extends Thread{

    private FTPClient ftp;
    private final String SERVER_ADDRESS;
    private final String USERNAME;
    private final String PASSWORD;
    public String msg;


    /**
     * Initializes FTPAccessClient Object.
     * <p>
     * The server-address has to either be a domain or a direct IP.
     * Username and Password arent always needed
     *
     * @param serverAddress     Address of the FTP server to connect to
     * @param username          Username (not always used)
     * @param password          Password (not always used)
     */
    public FTPAccessClient(String serverAddress, String username, String password){
        ftp = new FTPClient();
        FTPClientConfig config = new FTPClientConfig();
        ftp.configure(config);

        this.SERVER_ADDRESS = serverAddress;
        this.USERNAME = username;
        this.PASSWORD = password;
    }

    /**
     * Tries to connect to the server.
     * <p>
     * Returns false if connection is succesful and true if there was an error.
     * @return boolean
     */
    public boolean connect(){
        boolean error = false;

        try {
            int reply;
            ftp.connect(SERVER_ADDRESS);
            System.out.println("Connected to " + SERVER_ADDRESS + "!");
            String replyString = ftp.getReplyString();
            System.out.println(replyString);

            this.msg = replyString;

            reply = ftp.getReplyCode();

            if(!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                System.err.println("FTP server refused connection.");
                error = true;
            }
        }catch (Exception e){
            error = true;
        }
        return error;
    }

    /**
     * Tries to log in.
     * <p>
     * Tries to log into the server with credentials specified in the constructor.
     * Returns "true" if the log in was succesful, "false" if the log in was rejected
     * and "na" if the credentials arent specified.
     * @return String
     */
    public String login(){
        if (!USERNAME.equals("")){
            boolean succes = false;
            try {
                succes = ftp.login(USERNAME, PASSWORD);
            } catch (IOException e) {}
            if (succes){
                return "true";
            }
        }else {
            return "na";
        }

        try {
            ftp.logout();
        } catch (IOException e) {}
        return "false";
    }

    /**
     * Returns a message according to the input.
     *
     * @param text  Input is the output from login()
     * @return String
     */
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

    /**
     * Gets all available files on the root of the server and returns them as a FTPFile array.
     *
     * @return FTPFile
     */
    public FTPFile[] getFTPFile(){
        try {
            return ftp.listFiles();
        } catch (IOException e) {
        }
        return new FTPFile[0];
    }

    /**
     * Outputs filenames as ArrayList
     * <p>
     * Gets FTPFile array from getFTPFile(), gets filenames from said array and puts it into the ArrayList.
     *
     * @return ArrayList
     */
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

    /**
     * Deletes a file on the server
     * <p>
     * Tries to delete the specified file on the server. Returns true if it was succesful,
     * false if it wasnt.
     *
     * @param fileToDelete  File you want to delete on the server
     * @return boolean
     */
    public boolean delete(String fileToDelete) {
        try {
            ftp.deleteFile(fileToDelete);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Returns FTPClient object from Apache commons library
     *
     * @return FTPClient
     */
    public FTPClient getFtp() {
        return ftp;
    }

    /**
     * Returns the server address of the server, the client is connected to.
     *
     * @return String
     */
    public String getSERVER_ADDRESS(){
        return SERVER_ADDRESS;
    }

    /**
     * Checks if a file exists
     * <p>
     * Tries to open an InputStream to the specified file. Return false if InputStream
     * fails or if the server sends an error code (= file doesnt exist).
     * Returns true if the method could initialize InputStream (= file does exist).
     *
     * @param fn            Filename on the server
     * @return              boolean
     * @throws IOException  If file wasnt found
     */
    public boolean fileExists(String fn) throws IOException {
        InputStream inputStream = ftp.retrieveFileStream(fn);
        int returnCode = ftp.getReplyCode();

        if (inputStream == null || returnCode == 550){
            return false;
        }

        return true;
    }
}
