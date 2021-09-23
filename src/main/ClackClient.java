package main;

import data.ClackData;

/**
 * A class that represents the client user
 */
public class ClackClient {

    private String userName;
    private String hostName;
    private int port;
    private boolean closeConnection;
    private ClackData dataToSendToServer;
    private ClackData dataToReceiveFromServer;

public ClackClient(String userName ,String hostNme,int port){
    if(userName ==null || hostName ==null || port <1000){
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

public ClackClient(String userName ,String hostNme){
    this(userName, hostNme,7000);

}
public ClackClient(String userName){
    this(userName,"localhost",7000);

}

    /**
     * for Annoymous used not finished yet
     */
    public ClackClient () {

}

public void start(){

}
public void sendData(){

}
public void receiveData(){

}
public String printData(){
    return printData();
}

public String getUserName(){
    return userName;
}
public String getHostName(){
    return hostName;
}
public int getPort(){
    return port;
}
public int hashCode(){
return (int) userName.hashCode();
}
public  boolean equals(){

    return equals();
}
public  String toString(){

    return toString();
}
}
