package main;

import data.ClackData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ServerSideClientIO implements Runnable {
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
    * ClackServer object representing the master server
    */
   private ClackServer server;
   /**
    * Socket object representing the socket accepted from the client
    */
   private Socket clientSocket;

   /**
    *
    *
    * @param server - ClackServer object representing the master server
    * @param clientSocket -  Socket object representing the socket accepted from the client
    */
   public ServerSideClientIO (ClackServer server, Socket clientSocket){
      this.server = server;
      this.clientSocket = clientSocket;
      closeConnection = false;
      dataToReceiveFromClient = null;
      dataToSendToClient = null;

   }

   /**
    *
    */
   @Override
   public void run(){
      try {
         outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
         inFromClient = new ObjectInputStream(clientSocket.getInputStream());

         while (!closeConnection) {
            receiveData();
            if (!closeConnection) {
               this.server.broadcast();
               ClackServer.remove();
            }
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   /**
    * receives data from the client
    */
   public void receiveData(){


   }

   /**
    * sends data to the corresponding client
    *
    * broadcast() calls this method
    */
   public void sendData() {
      try {
         outToClient.writeObject(dataToSendToClient);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   /**
    * sets the ClackData variable dataToSendToClient
    * @param dataToSendToClient - ClackData object representing data sent to client
    */
   public void setDataToSendToClient(ClackData dataToSendToClient){
      this.dataToReceiveFromClient = dataToSendToClient;
   }

}
