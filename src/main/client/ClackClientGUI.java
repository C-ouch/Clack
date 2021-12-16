package main.client;

import data.ClackData;
import data.FileClackData;
import data.MessageClackData;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Scanner;
import java.util.Vector;

class ClackClientGUI extends GUIDesign {

    MessageStorage messageStorage = new MessageStorage();
    String address;
    String port_str;
    ClackClientGUI self;

    /**
     * String of name of the client
     */
    private String userName;
    /**
     * String of name of the computer using the server
     */
    private String hostName;
    /**
     * integer of port number on server connect to
     */
    private int port;
    /**
     * boolean representing whether connection is close or not
     */
    private boolean closeConnection;
    /**
     * private data send to server
     */
    private ClackData dataToSendToServer;
    /**
     * private data receive from server
     */
    private ClackData dataToReceiveFromServer;

    /**
     * private in and out stream
     */
    private ObjectOutputStream outToServer;
    private ObjectInputStream inFromServer;

    /**
     * get InputStream from ClackClient
     * @return InputStream
     */
    public ObjectInputStream getInputStream() {
        return inFromServer;
    }

    /**
     * used to input from stdin
     */
    private Scanner inFromStd;
    //private final String key = "encryption";

    /**
     * Client side server listener
     */

    DefaultListModel<String> user_list_model;


    public ClackClientGUI(String _address, String _port, String _username) {
        super();
        self = this;

        u_text_area.setContentType("text/html");
        u_text_area.setText(
                messageStorage.build_message_html()
        );
        u_text_area.setEditable(false);

        u_text_area.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    System.out.println(e.getURL());
                    String index_str = e.getURL().toString().split("//")[1];
                    int index = Integer.parseInt(index_str);
                    MessageStorage.FileMessage file_message = (MessageStorage.FileMessage) messageStorage.messages.get(index);

                    if(file_message.mime.startsWith("image")) {
                        new ImagePreviewDialog(Base64.getDecoder().decode(file_message.base64_file)).setVisible(true);
                    }
                    else if (file_message.mime.startsWith("audio")) {
                        new AudioPreviewDialog(Base64.getDecoder().decode(file_message.base64_file)).setVisible(true);
                    }
                    else{
                        JFileChooser chooser=new JFileChooser(".");
                        chooser.setFileSelectionMode(chooser.FILES_AND_DIRECTORIES);
                        chooser.setDialogTitle("Save File");
                        chooser.setCurrentDirectory(new File(System.getProperties().getProperty("user.home")));
                        chooser.setFileFilter(new javax.swing.filechooser.FileFilter()
                        {
                            public boolean accept(final File f)
                            {
                                return ! f.isDirectory();
                            }

                            public String getDescription()
                            {
                                return "All files (*.*)";
                            }
                        });

                        int returnVal1 = chooser.showSaveDialog(self);
                        if (returnVal1 == JFileChooser.APPROVE_OPTION)
                        {
                            File file = chooser.getSelectedFile();
                            // MessageStorage.FileMessage file_message = (MessageStorage.FileMessage) messageStorage.messages.get(index);
                            try{
                                Files.write(file.toPath(), Base64.getDecoder().decode(file_message.base64_file));
                            }
                            catch (IOException ex){
                                ex.printStackTrace(System.err);
                            }
                        }
                    }
                }
            }
        });

        address = _address;
        port_str = _port;
        userName = _username;
        hostName = address;
        port = Integer.parseInt(port_str);

        closeConnection = false;
        dataToReceiveFromServer = null;
        dataToSendToServer = null;

        user_list_model = new DefaultListModel<>();
        u_user_list.setModel(user_list_model);

        try {
            Socket skt = new Socket(this.hostName, this.port);
            outToServer = new ObjectOutputStream(skt.getOutputStream());
            inFromServer = new ObjectInputStream(skt.getInputStream());
        }
        catch (Exception e) {
            System.err.println("Socket failed: " + e.getMessage());
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                send_thread_function();
            }
        }).start();

        new Thread(new ClientSideServerListenerGUI(self)).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                list_user_thread_function();
            }
        }).start();

        u_send_message.addActionListener(
                new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        String message = u_message_input.getText().trim();
                        if(media_file != null){
                            dataToSendToServer = new FileClackData(userName, media_file.getAbsolutePath(), ClackData.CONSTANT_SENDFILE);
                            send_message(dataToSendToServer);
                            media_file = null;
                            u_message_input.setText("");
                        }
                        else if (message.length() > 0) {
                            dataToSendToServer = new MessageClackData(userName, message, ClackData.CONSTANT_SENDMESSAGE);
                            send_message(dataToSendToServer);
                            media_file = null;
                            u_message_input.setText("");
                        }
                        else {
                            JOptionPane.showMessageDialog(self, "Please enter a message to send.");
                        }
                    }
                }
        );

        u_load_media.addActionListener(
                new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        JFileChooser fileChooser = new JFileChooser();
                        int returnValue = fileChooser.showOpenDialog(self);
                        if (returnValue == JFileChooser.APPROVE_OPTION) {
                            media_file = fileChooser.getSelectedFile();
                        }
                    }
                }
        );

    }

    File media_file = null;



    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClackClientGUI frame = new ClackClientGUI("127.0.0.1", "7000", "Aero");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void list_user_thread_function() {
        while (!closeConnection) {
            System.err.println("list_user_thread_function called!");
            ClackData data = new MessageClackData(userName, "", ClackData.CONSTANT_LISTUSERS);
            send_message(data);
            try{
                Thread.sleep(1000);
            }
            catch (Exception e) {
                System.err.println("Sleep failed: " + e.getMessage());
            }
        }
    }


    public void receive_thread_function() {
        while(!closeConnection){
            try {
                System.err.println("receive_thread_function: << waiting for data");
                dataToReceiveFromServer = (ClackData) inFromServer.readObject();
                System.err.println("receive_thread_function: data received >>");
                on_receive_messsage(dataToReceiveFromServer);
            } catch (ClassNotFoundException | IOException ex) {
                System.err.println("Read failed: " + ex.getMessage());
            }
        }
    }

    Vector<ClackData> packages_to_send = new Vector<>();

    public void send_thread_function() {
        while(!closeConnection){
            if(packages_to_send.size() > 0){
                System.err.println("send_thread_function: Size of packages_to_send: " + packages_to_send.size());
            }

            while(packages_to_send.size() > 0){
                ClackData package_to_send = packages_to_send.get(0);
                packages_to_send.remove(0);
                try {
                    System.err.println("send_thread_function: << sending data");
                    outToServer.writeObject(package_to_send);
                    System.err.println("send_thread_function: data sent >>");
                    System.err.println("Sending list users request");
                } catch (IOException ex) {
                    System.err.println("Write failed: " + ex.getMessage());
                }
            }
        }
    }

    public void notify_update_ui() {
        u_text_area.setText(
                messageStorage.build_message_html()
        );
    }

    public void on_receive_messsage(ClackData clackData) {
        if(clackData.getType() == ClackData.CONSTANT_LISTUSERS){
            System.err.println("List users:");
            System.err.println(clackData.getData());

            user_list_model.clear();

            for (String line: clackData.getData().split("\n")) {
                line = line.trim();
                if(line.length() == 0){
                    continue;
                }
                user_list_model.add(user_list_model.size(), line);
            }
        }
        else{
            if(clackData instanceof FileClackData) {
                FileClackData fileClackData = (FileClackData) clackData;
                messageStorage.messages.add(
                        new MessageStorage.FileMessage(clackData.getUserName(), fileClackData.getData(), fileClackData.mime)
                );
            }
            else if(clackData instanceof MessageClackData) {
                MessageClackData messageClackData = (MessageClackData) clackData;
                messageStorage.messages.add(
                        new MessageStorage.Message(clackData.getUserName(), messageClackData.getData())
                );
            }
            else {
                System.err.println("Unknown data type");
            }
        }

        notify_update_ui();
    }

    public synchronized void send_message(ClackData clackData) {
        packages_to_send.add(clackData);
    }




}

