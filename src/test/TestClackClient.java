package test;
import data.ClackData;


public class TestClackClient {

    private String userName;
    private String hostName;
    private int port;

    public TestClackClient(String userName, String hostNme, int port) {
        if (userName == null || hostName == null || port < -2) {
            System.out.println("Input for username ,hostname,and port is invalid");


        } else {
            this.userName = userName;
            this.hostName = hostNme;
            this.port = port;
            System.out.println("invalid");

        }


    }
}