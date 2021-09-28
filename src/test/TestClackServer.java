package test;
import data.ClackData;

/**
 * @author Elek Ye and Evan Couchman
 * A class that represents the client user 
 */
public class TestClackServer{


    /** integer representing port number on server*/
    private int port;
    /** boolean representing the server is accepting any clients or not*/
    private boolean closeConnection;
    /**private data receive or send to client*/
    private TestClackData dataToReceiveFromClient;
    private TestClackData dataToSendToClient;
    /** public constructor set the integer of port witch is 7000*/
    public TestClackServer(int port) {
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
    /** public constructor set the integer of port witch is 7000*/
    public TestClackServer(){
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
    @override
    public int hashCode(){
        return port;
    }
    @override
    public boolean equals(Object other){
       test.TestClackServer otherClient =(test.TestClackServer) other;
        return this.port == otherClient.port;
    }
    @override
    public String toString(){
        return toString();
    }
}