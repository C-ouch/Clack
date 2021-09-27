package main;
import data.ClackData;
import data.MessageClackData;
public class ClackServer{



    private int port;
    private boolean closeConnection;
    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;

    public ClackServer(int port) {
        if (dataToReceiveFromClient == null|| dataToSendToClient == null ||port <1000)
        {
            System.out.println(" port need to above 1000");
        }else{
          this.port =port;
          closeConnection=false;
          dataToReceiveFromClient=null;
          dataToSendToClient=null;
        }

    }

    public ClackServer(){
        this(7000);
    }
    public void start(){

    }
    public void receiveData(){

    }
    public void sendData(){

    }
    public int getPort(){
        return port;
    }
    public int hashCode(){
        return port ;
    }
    public boolean equals(Object other){
        ClackServer otherClient =(ClackServer) other;
        return this.port == otherClient.port;
    }
    public String toString(){
        return toString();
    }
}
