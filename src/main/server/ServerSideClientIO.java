package main.server;

import data.ClackData;
import data.MessageClackData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSideClientIO implements Runnable {

   /**
    * Flag to indicate if the client is still connected.
    */
   boolean closeConnection;

   /**
    * Data field
    */
   ClackData dataToReceiveFromClient;
   ClackData dataToSendToClient;

   /**
    * Socket IO stream
    */
   ObjectInputStream inFromClient;
   ObjectOutputStream outToClient;

   /**
    * Clack Server object
    */
   ClackServer server;

   /**
    * Client socket
    */
   Socket clientSocket;

   /**
    * Constructor
    * @param server ClackServer object
    * @param clientSocket Client socket
    */
   public ServerSideClientIO(ClackServer server, Socket clientSocket) {
      this.server = server;
      this.clientSocket = clientSocket;
      closeConnection = false;
      dataToReceiveFromClient = null;
      dataToSendToClient = null;
      inFromClient = null;
      outToClient = null;
   }

   /**
    * Run method
    */
   @Override
   public void run() {
      try {
         outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
         inFromClient = new ObjectInputStream(clientSocket.getInputStream());

         while (!closeConnection) {
            receiveData();
            if(dataToReceiveFromClient != null){
               setDataToSendToClient(dataToReceiveFromClient);
               server.broadcast(dataToReceiveFromClient);
            }
         }

         outToClient.close();
         inFromClient.close();

      } catch (IOException ioe) {
         System.err.println("An unexpected IO Exception has occured with the socket");
      }

   }

   /**
    * Recieve data from client and checks that the connection is open or closed
    */
   public void receiveData() {
      dataToReceiveFromClient = null;
      System.err.println("receiveData() called!");
      try {
         dataToReceiveFromClient = null;
         dataToReceiveFromClient = (ClackData) inFromClient.readObject();

         if (dataToReceiveFromClient.getType() == ClackData.CONSTANT_LOGOUT) {
            closeConnection = true;
            server.remove(this);
         }

         if (dataToReceiveFromClient.getType() == ClackData.CONSTANT_LISTUSERS) {
            String users_info = "";
            for(ServerSideClientIO client : server.serverSideClientIOList) {
               users_info += client.clientSocket.getRemoteSocketAddress().toString() + ":" + client.clientSocket.getPort() + "\n";
            }
            setDataToSendToClient(new MessageClackData("Server", users_info, ClackData.CONSTANT_LISTUSERS));
            sendData();
            dataToReceiveFromClient = null;
         }

      } catch (IOException | ClassNotFoundException ioe) {
         System.err.println(ioe.getMessage() + " : ");
         ioe.printStackTrace(System.err);
         closeConnection = true;
         server.remove(this);

      }
   }

   /**
    * Send data to the client
    */
   public void sendData() {
      try {
         outToClient.writeObject(dataToSendToClient);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   /**
    * Set the data to send to the client
    * @param dataToSendToClient ClackData object
    */
   public void setDataToSendToClient(ClackData dataToSendToClient) {
      this.dataToSendToClient = dataToSendToClient;
   }




}
