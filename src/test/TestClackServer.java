package test;
import data.ClackData;


public class TestClackServer{



    private int port;
    private boolean closeConnection;
    private TestClackData dataToReceiveFromClient;
    private TestClackData dataToSendToClient;

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

    public TestClackServer(){
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
        return port;
    }
    public boolean equals(Object other){
       test.TestClackServer otherClient =(test.TestClackServer) other;
        return this.port == otherClient.port;
    }
    public String toString(){
        return toString();
    }
}