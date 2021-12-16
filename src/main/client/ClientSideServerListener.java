package main.client;

public class ClientSideServerListener implements Runnable {

    /**
     * The client side server listener.
     */
    ClackClient client;

    /**
     * Constructor
     * @param client
     */
    public ClientSideServerListener(ClackClient client) {
        this.client = client;
    }

    /**
     * The run method, receive and print data from the server.
     */
    @Override
    public void run() {
        client.receiveData();
        client.printData();
    }
}