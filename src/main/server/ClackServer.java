package main.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import data.ClackData;


public class ClackServer {

    /**
     * integer representing port number on server
     */
    private int port;
    /**
     * boolean representing the server is accepting any clients or not
     */
    private boolean closeConnection;

    /**
     * Uses command line arguments to create a new
     * ClackServer object, and starts the ClackServer object.
     * Making sue each arguments get passed to correct constructor.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        ClackServer c;

        if (args.length == 0) {
            c = new ClackServer();
            c.start();
        } else {
            try {
                int portnum = Integer.parseInt(args[0]);
                c = new ClackServer(portnum);
                c.start();
            } catch (NumberFormatException e) {
                System.err.println("Expect an integer for the port number!");
            }
        }
    }

    ArrayList<ServerSideClientIO> serverSideClientIOList;

    /**
     * Constructor to set port
     *
     * @param port port for the server
     */
    public ClackServer(int port) throws IllegalArgumentException {
        if (port < 1024) {
            throw new IllegalArgumentException("Port needs to be more than 1024");
        }
        this.port = port;

        serverSideClientIOList = new ArrayList<>();

    }

    /**
     * default constructor set the integer of port witch is 7000
     */
    public ClackServer() {
        this(7000);
    }

    /**
     * public constructor set start
     *This method starts looking for
     * communication with the client
     *
     * While the connection is open, the start() method receives data from a client, and echoes it
     * back to the client.
     */
    public void start() {
        try {
            ServerSocket sskt = new ServerSocket(this.port);
            //Socket clientSocket = sskt.accept();
            //outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
            //inFromClient = new ObjectInputStream(clientSocket.getInputStream());

            while (!closeConnection) {
                Socket clientSocket = sskt.accept();
                ServerSideClientIO serverSideClientIO = new ServerSideClientIO(this, clientSocket);
                serverSideClientIOList.add(serverSideClientIO);
                new Thread(serverSideClientIO).start();

            }

            for(ServerSideClientIO serverSideClientIO : serverSideClientIOList) {
                // serverSideClientIO.close();
            }

            //inFromClient.close();
            //outToClient.close();
            sskt.close();

        } catch (IOException ioe) {

            System.err.println("An unexpected IO Exception has occured with the socket");
        }

    }

    public synchronized void broadcast(ClackData dataToBroadcastToClients) {
        for (ServerSideClientIO serverSideClientIO : serverSideClientIOList) {
            serverSideClientIO.setDataToSendToClient(dataToBroadcastToClients);
            serverSideClientIO.sendData();
        }
    }

    public synchronized void remove(ServerSideClientIO serverSideClientToRemove) {
        serverSideClientIOList.remove(serverSideClientToRemove);
        try{
            serverSideClientToRemove.inFromClient.close();
        }
        catch (IOException ioe) {
            System.err.println("An unexpected IO Exception has occured with the socket");
        }
        try{
            serverSideClientToRemove.outToClient.close();
        }
        catch (IOException ioe) {
            System.err.println("An unexpected IO Exception has occured with the socket");
        }
        try{
            serverSideClientToRemove.clientSocket.close();
        }
        catch (IOException ioe) {
            System.err.println("An unexpected IO Exception has occured with the socket");
        }

    }

    /**
     * gets port number from client
     *
     * @return port- port number for server
     */
    public int getPort() {
        return port;
    }

    @Override
    public int hashCode() {
        int r = 17;

        r = 37 * r + port;
        r = 37 * r + (closeConnection ? 0 : 1);
        // r = 37 * r + (dataToSendToClient == null ? 0 : dataToSendToClient.hashCode()); //ClackData Object
        // r = 37 * r + (dataToReceiveFromClient == null ? 0 : dataToReceiveFromClient.hashCode()); //ClackData Object

        return r;
    }

    @Override
    public boolean equals(Object other) {
        return this == other;
    }

    @Override
    public String toString() {
        return "<ClackServer 0x" + this.hashCode() + ">";
    }
}
