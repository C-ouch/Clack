package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.ServerSocket;
import java.net.Socket;

import data.ClackData;
import data.MessageClackData;

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
     * private data receive or send to client
     */
    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;

    /**
     * private in and out stream
     */
    private ObjectOutputStream outToClient;
    private ObjectInputStream inFromClient;

    /**
     * Uses command line arguments to create a new
     * ClackServer object, and starts the ClackServer object.
     *
     * @param args command line arguments
     */
    public static void main(String[] args)
    {
        ClackServer c;

        if (args.length == 0) {
            c = new ClackServer();
            c.start();
        } else {
            int portnum = Integer.parseInt(args[0]);
            c = new ClackServer(portnum);
            c.start();
        }
    }


    /**
     * Constructor to set port
     * @param port port for the server
     */
    public ClackServer(int port) {
        try{
            if(port< 1024){
                throw new IllegalArgumentException("Port needs to be more than 1024");
            }
            this.port = port;
            dataToReceiveFromClient = null;
            dataToSendToClient = null;
            outToClient = null;
            inFromClient = null;

        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
        }
    }

    /**
     * default constructor set the integer of port witch is 7000
     */
    public ClackServer() {
        this(7000);
    }

    /**
     * public constructor set start
     */
    public void start() {
        try {
            ServerSocket sskt = new ServerSocket(this.port);
            Socket clientSocket = sskt.accept();
            outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
            inFromClient = new ObjectInputStream(clientSocket.getInputStream());

            while (!closeConnection) {
                receiveData();
                dataToSendToClient = dataToReceiveFromClient;
                if (!closeConnection) {
                    sendData();
                }
            }

            inFromClient.close();
            outToClient.close();
            sskt.close();

        } catch (IOException ioe) {

            System.err.println("An unexpected IO Exception has occured with the socket");
        }

    }

    /**
     *Recieve data from client and checks that the connection is open or closed
     */
    public void receiveData() {
        try {
            dataToReceiveFromClient = (ClackData) inFromClient.readObject();

            if (dataToReceiveFromClient.getType() == ClackData.CONSTANT_LOGOUT) {
                closeConnection = true;
            }
        } catch  (IOException | ClassNotFoundException ioe){
            System.err.println(ioe.getMessage());

        }
}

    /**
     *  send data  to client
     */
    public void sendData() {
        try {
            outToClient.writeObject(dataToSendToClient);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  gets port number from client
     * @return port  port number for server
     */
    public int getPort() {
        return port;
    }

    @Override
    public int hashCode() {
        int r = 17;

        r = 37 * r + port;
        r = 37 * r + (closeConnection ? 0 : 1);
        r = 37 * r + (dataToSendToClient == null ? 0 : dataToSendToClient.hashCode()); //ClackData Object
        r = 37 * r + (dataToReceiveFromClient == null ? 0 : dataToReceiveFromClient.hashCode()); //ClackData Object

        return r;
    }

    @Override
    public boolean equals(Object other) {
        return true;
    }

    @Override
    public String toString() {
        return toString();
    }
}
