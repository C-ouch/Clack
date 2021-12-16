package main.client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

class GUIDesign extends JFrame {

    public JPanel contentPane;
    public JEditorPane u_text_area;
    public JList u_user_list;
    public JButton u_send_message;
    public JButton u_load_media;
    public JTextField u_message_input;

    /**
     * Create the frame.
     */
    public GUIDesign() {
        setTitle("Clack");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 670);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        u_text_area = new JTextPane();
        u_text_area.setBounds(10, 10, 685, 580);
        contentPane.add(u_text_area);

        u_user_list = new JList();
        u_user_list.setBounds(705, 10, 179, 611);
        contentPane.add(u_user_list);

        u_message_input = new JTextField();
        u_message_input.setBounds(10, 600, 479, 21);
        contentPane.add(u_message_input);
        u_message_input.setColumns(10);

        u_load_media = new JButton("Load file..");
        u_load_media.setBounds(602, 599, 93, 23);
        contentPane.add(u_load_media);

        u_send_message = new JButton("Send");
        u_send_message.setBounds(499, 599, 93, 23);
        contentPane.add(u_send_message);
    }
}
