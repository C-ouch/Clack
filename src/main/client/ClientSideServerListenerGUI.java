package main.client;

public class ClientSideServerListenerGUI implements Runnable {

    /**
     * The client side server listener.
     */
    ClackClientGUI client_gui;

    /**
     * Constructor
     * @param client_gui
     */
    public ClientSideServerListenerGUI(ClackClientGUI client_gui) {
        this.client_gui = client_gui;
    }

    /**
     * The run method, receive and print data from the server.
     */
    @Override
    public void run() {
        client_gui.receive_thread_function();

    }

}