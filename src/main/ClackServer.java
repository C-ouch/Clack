package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
     * public constructor set the integer of port witch is 7000
     */
    public ClackServer(int port) {
        if (dataToReceiveFromClient == null || dataToSendToClient == null || port < 7000) {
            System.out.println(" port need to above 7000");
        } else {
            closeConnection = false;
            this.port = port;
            dataToReceiveFromClient = null;
            dataToSendToClient = null;
            outToClient = null;
            inFromClient = null;
        }

    }

    /**
     * public constructor set the integer of port witch is 7000
     */
    public ClackServer() {
        this(7000);
    }

    /**
     * public constructor set start
     */
    public void start() {
        try {
            //int clientPort = getPort();
            ServerSocket sskt = new ServerSocket(port);
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

            System.err.println(ioe.getMessage());
        }

    }

    /**
     *need to still see if I need to see what problems needed to be caught
     */
    public void receiveData() {
        try {
            dataToReceiveFromClient = (ClackData) inFromClient.readObject();

            if (dataToReceiveFromClient.getType() == ClackData.CONSTANT_LOGOUT) {
                closeConnection = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *  send data  to client
     */
    public void sendData() {
        try {

            outToClient.writeObject(dataToSendToClient);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *  gets data  from client
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
