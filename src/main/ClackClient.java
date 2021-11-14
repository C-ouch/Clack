package main;

import java.io.IOException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.UnknownHostException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;
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
    private final String key = "encryption";

    Socket skt;

    public static void main(String[] args) {
        String str = "sherlock@192.168.23.45:12415";
        String username = "Default Username ";
        String port = "Default port";
        String ip = "";

        if (str != null) {
            Integer at = str.indexOf("@");
            if (at > 0) {
                username = str.substring(0, at);
                ip = str.substring((at + 1));
                Integer colon = ip.indexOf(":");
                if (colon > 0) {
                    port = ip.substring(colon + 1);
                    ip = ip.substring(0, colon);
                }

            } else {
                username = str;
            }
            System.out.println("Username =" + username);
            System.out.println("IP" + ip);
            System.out.println("port" + port);

        }

        /*ClackClient c;

        if (args.length == 0) {
            c = new ClackClient();
        } else {

            //need to check/test if username or other traits are within the string argument
            //then put them into a new object for the correct constructor
             username = args[0];



            c = new ClackClient(username);
        }

        c.start();*/
    }

    /**
     * constructor to create User name, host name and port are given
     *
     * @param userName User Name for the Client
     * @param hostName Hostname for the client
     * @param port     port for the client
     */
    public ClackClient(String userName, String hostName, int port) {
        try {
            this.userName = userName;
            this.hostName = hostName;
            this.port = port;
            closeConnection = false;
            dataToReceiveFromServer = null;
            dataToSendToServer = null;

            if (userName == null)
                throw new IllegalArgumentException("userName should not be null!");
            if (hostName == null)
                throw new IllegalArgumentException("hostName should not be null!");
            if (port < 1024)
                throw new IllegalArgumentException("port should not b less than 1024!");
        }catch (IllegalArgumentException iae) {
            System.err.println("Illegal argument found: " + iae.getMessage());
        }
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
        try {
            Socket skt = new Socket(this.hostName, this.port);
            outToServer = new ObjectOutputStream(skt.getOutputStream());
            inFromServer = new ObjectInputStream(skt.getInputStream());

            while (!closeConnection) {
                readClientData();
                sendData();
                receiveData();
                printData();
                }

            inFromStd.close();
            inFromServer.close();
            outToServer.close();
            skt.close();


        } catch ( UnknownHostException uhe ) {
            System.err.println( "Host not known: " + uhe.getMessage() );
        } catch ( NoRouteToHostException nrhe ) {
            System.err.println( "Route to host not available" );
        } catch ( ConnectException ce ) {
            System.err.println( "Connection Refused" );
        } catch ( IOException ioe ) {
            System.err.println( "IO Exception generated: " + ioe.getMessage() );
        }
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
        } catch (ClassNotFoundException | IOException cnf) {
            System.err.println("Read failed: " + cnf.getMessage());
            //e.printStackTrace();
        }
    }

    /**
     * prints the received data to standard output
     */
    public void printData() {
        /* use dataToSendToServer as dataToReceiveFromServer in project2 */
        dataToReceiveFromServer = dataToSendToServer;
        if (dataToReceiveFromServer == null) {
            System.out.println("The reference is null, there is no data to print");
        }else if (dataToReceiveFromServer instanceof FileClackData) {
            System.out.println(getHostName() + " sent a file: " + ((FileClackData)dataToReceiveFromServer).getFileName());
            System.out.println("with contents: " + dataToReceiveFromServer.getData(key));
            System.out.println("with type: " + dataToReceiveFromServer.getType());
        } else if (dataToReceiveFromServer instanceof MessageClackData) {
            System.out.println(getHostName() + " sent a message with contents: " + dataToReceiveFromServer.getData(key));
            System.out.println("with type: " + dataToReceiveFromServer.getType());
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
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        ClackClient client = (ClackClient) other;
        return port == client.port &&
                closeConnection == client.closeConnection &&
                Objects.equals(userName, client.userName) &&
                Objects.equals(hostName, client.hostName) &&
                Objects.equals(dataToReceiveFromServer, client.dataToReceiveFromServer) &&
                Objects.equals(dataToSendToServer, client.dataToSendToServer);
    }

    @Override
    public String toString() {
        return "username:" + userName + ", hostname:" + hostName + ", port:" + port+ ", closeConnection=" + closeConnection +
                ", dataToReceiveFromClient=" + dataToReceiveFromServer +
                ", dataToSendToClient=" + dataToSendToServer;    }

}
