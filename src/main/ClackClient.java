package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import data.ClackData;
import data.FileClackData;
import data.MessageClackData;

/**
 * The ClackClient represent the client user.
 *
 * @author Elek Ye and Evan Couchman
 */
public class ClackClient {
    /**
     * String of name of the client
     */
    private String userName;
    /**
     * String of name of the computer using the server
     */
    private String hostName;
    /**
     * integer of port number on server connect to
     */
    private int port;
    /**
     * boolean representing whether connection is close or not
     */
    private boolean closeConnection;
    /**
     * private data send to server
     */
    private ClackData dataToSendToServer;
    /**
     * private data receive from server
     */
    private ClackData dataToReceiveFromServer;

    /**
     * private in and out stream
     */
    private ObjectOutputStream outToServer;
    private ObjectInputStream inFromServer;

    /**
     * used to input from stdin
     */
    private Scanner inFromStd;

    Socket skt;

    public static void main(String[] args) {
//        System.out.println("Number of arguments: " + args.length);
//        for (int i = 0; i < args.length; i++) {
//            System.out.println("Argument #" + i + ": " + args[i]);
//        }

        ClackClient c;

        if (args.length == 0) {
            c = new ClackClient();
        } else {

            //need to check/test if username or other traits are within the string argument
            //then put them into a new object for the correct constructor
            String username = args[0];



            c = new ClackClient(username);
        }

        c.start();
    }

    /**
     * constructor to create User name, host name and port are given
     *
     * @param userName User Name for the Client
     * @param hostName Hostname for the client
     * @param port     port for the client
     */
    public ClackClient(String userName, String hostName, int port) {
        if (userName == null) {
            throw new IllegalArgumentException("userName should not be null!");
        }

        if (hostName == null) {
            throw new IllegalArgumentException("hostName should not be null!");
        }

        if (port < 1024) {
            throw new IllegalArgumentException("port should not b less than 1024!");
        }

        this.userName = userName;
        this.hostName = hostName;
        this.port = port;
        closeConnection = false;
        dataToReceiveFromServer = null;
        dataToSendToServer = null;

    }

    /**
     * This Circle constructor initializes the ClackClient User names ,host name and
     * the port number
     *
     * @param hostName Hostname for the client
     * @param userName User Name for the Client port set as 7000
     */
    public ClackClient(String userName, String hostName) {
        this(userName, hostName, 7000);

    }

    /**
     * constructor that sets host name to be "localhost"
     *
     * @param userName the specified username
     */
    public ClackClient(String userName) {
        this(userName, "localhost", 7000);

    }

    /**
     * default constructor that sets anonymous user
     */
    public ClackClient() {
        this("anonymous", "localhost", 7000);
    }

    /**
     * method to start a connection
     */
    public void start() {
        inFromStd = new Scanner(System.in);
        inFromServer.close();
    }

    /**
     * reads the data from the client
     */
    public void readClientData() {
        if (inFromStd == null || closeConnection) {
            return;
        }

        System.out.print("command>");
        String lineString = inFromStd.nextLine();
        if (lineString == null) {
            closeConnection = true;
            return;
        }

        lineString = lineString.trim();

        String[] arguments = lineString.split("s+");

        if ("DONE".equals(arguments[0])) {
            closeConnection = true;
            dataToSendToServer = new MessageClackData(this.userName, "",
                    ClackData.CONSTANT_LOGOUT);
            return;
        }

        if ("SENDFILE".equals(arguments[0])) {
            /* the command is SENDFILE filename */
            if (arguments.length != 2) {
                System.err.println("SENDFILE: lack argument");
                return;
            }

            String filename = arguments[1];
            FileClackData fileClackData = new FileClackData(this.userName, filename, ClackData.CONSTANT_SENDFILE);
            try {
                fileClackData.readFileContents();
                dataToSendToServer = fileClackData;
            } catch (IOException e) {
                System.err.println("read failed on file " + filename);

                dataToSendToServer = null;
            }
            return;
        }

        if ("LISTUSERS".equals(arguments[0])) {
            // do nothing now
            return;
        }

        // initialize dataToSendToServer as a MessageClackData
        MessageClackData messageClackData = new MessageClackData(this.userName, lineString,
                ClackData.CONSTANT_SENDMESSAGE);
        dataToSendToServer = messageClackData;
    }

    /**
     * sends data to server
     */
    public void sendData() {
        try {

            outToServer.writeObject(dataToSendToServer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * receives data from the server
     */
    public void receiveData() {
        try {
            dataToReceiveFromServer = (ClackData) inFromServer.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * prints the received data to standard output
     */
    public void printData() {
        /* use dataToSendToServer as dataToReceiveFromServer in project2 */
        dataToReceiveFromServer = dataToSendToServer;
        if (dataToReceiveFromServer == null) {
            return;
        }

        if (dataToReceiveFromServer.getUserName().equals(this.userName)) {
            System.out.println(dataToReceiveFromServer.getData());
        }
    }

    /**
     * @return the username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @return the hostname
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    @Override
    public int hashCode() {

        int r = 17;

        r = 37 * r + (userName == null ? 0 : userName.hashCode());
        r = 37 * r + (hostName == null ? 0 : hostName.hashCode());
        r = 37 * r + port;                         //Int Type
        r = 37 * r + (closeConnection ? 0 : 1);    //Boolean type
        r = 37 * r + (dataToSendToServer == null ? 0 : dataToSendToServer.hashCode()); //ClackData Object
        r = 37 * r + (dataToReceiveFromServer == null ? 0 : dataToReceiveFromServer.hashCode()); //ClackData Object
        r = 37 * r + (inFromStd == null ? 0 : inFromStd.hashCode()); //ClackData Object

        return r;
    }

    @Override
    public boolean equals(Object other) {

        return true;
    }

    @Override
    public String toString() {
        return "username:" + userName + ", hostname:" + hostName + ", port:" + port;
    }
}
