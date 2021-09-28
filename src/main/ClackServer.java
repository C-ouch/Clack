package main;
import data.ClackData;
import data.MessageClackData;
public class ClackServer{

    /**
     * @author Elek Ye and Evan Couchman
     * A class that represents the client user
     */

    /** integer representing port number on server*/
    private int port;
    /** boolean representing the server is accepting any clients or not*/
    private boolean closeConnection;
    /**private data receive or send to client*/
    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;
    /** public constructor set the integer of port witch is 7000*/
    public ClackServer(int port) {
        if (dataToReceiveFromClient == null|| dataToSendToClient == null ||port <7000)
        {
            System.out.println(" port need to above 7000");
        }else{
          this.port =port;
          closeConnection=false;
          dataToReceiveFromClient=null;
          dataToSendToClient=null;
        }

    }
    /** public constructor set the integer of port witch is 7000*/
    public ClackServer(){
        this(7000);
    }
    /** public constructor set start*/
    public void start(){

    }
   /** public constructor receivedata  from client*/
    public void receiveData(){

    }
    /** public constructor senddata  to client*/
    public void sendData(){

    }
    /** public constructor gets data  from client*/
    public int getPort(){
        return port;
    }
    @Override
    public int hashCode(){
        return port ;
    }
    @Override
    public boolean equals(Object other){
        ClackServer otherClient =(ClackServer) other;
        return this.port == otherClient.port;
    }
    @Override
    public String toString(){
        return toString();
    }
}
