package main;

import data.ClackData;

/**
 * @author  Elek Ye and Evan Couchman
 * A class that represents the client user
 */
public class ClackClient {
    /** String of name of the client*/
    private String userName;
    /** String of name of the coputer using the server*/
    private String hostName;
    /** interget  of port number on server connect to*/
    private int port;
    /** boolean representing whether connection is close or not*/
    private boolean closeConnection;
    /** private data send or receive from server*/
    private ClackData dataToSendToServer;
    private ClackData dataToReceiveFromServer;
/** constructor to creat User name , host name and port are given*/
public ClackClient(String userName ,String hostNme,int port){
    if(userName ==null || hostName ==null || port <7000){
        System.out.println("Input for username ,hostname,and port is invalid");


    }else{
        this.userName = userName;
        this.hostName = hostNme;
        this.port = port;
        closeConnection = false;
        dataToReceiveFromServer =null;
        dataToSendToServer = null;
    }

}
// Constructiors
/**
 * This Circle construtor initializes the ClackClient User names ,host name and the port number
 *
 * @param hostName Hostname for the client
 * @param userName User Name for the Client
 * @param port      port set as 7000
 * */
public ClackClient(String userName ,String hostName){
    this(userName, hostName,7000);

}
public ClackClient(String userName){
    this(userName,"localhost",7000);

}

    /**
     * for Annoymous used not finished yet
     */
    public ClackClient () {

}
/** construcor for start*/
public void start(){

}
    /** construcor for sendData*/
public void sendData(){

}
    /** construcor for receivedata*/
public void receiveData(){

}
    /** construcor for print data*/
    /**
     *
     * @return Data from the Client.
     */

public String printData(){
    return printData();
}
    /** construcor for get username*/
public String getUserName(){
    return userName;
}
    /** construcor for gethost name*/
public String getHostName(){
    return hostName;
}
    /** construcor for get port*/
public int getPort(){
    return port;
}
   @override
public int hashCode(){
return (int) userName.hashCode();
}
  @override
public  boolean equals(){

    return equals();
}
@override
public  String toString(){

    return toString();
}
}
