package main.client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Launcher extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField u_input_address;
    private JTextField u_input_port;
    private JButton u_button_ok;
    private JButton u_button_cancel;
    private JTextField u_input_username;
    Launcher self;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            Launcher dialog = new Launcher();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public Launcher() {
        self = this;

        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Address:");
        lblNewLabel.setBounds(10, 10, 54, 15);
        contentPanel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Port:");
        lblNewLabel_1.setBounds(10, 35, 54, 15);
        contentPanel.add(lblNewLabel_1);

        u_input_address = new JTextField();
        u_input_address.setBounds(74, 7, 350, 21);
        contentPanel.add(u_input_address);
        u_input_address.setColumns(10);

        u_input_port = new JTextField();
        u_input_port.setBounds(74, 32, 350, 21);
        contentPanel.add(u_input_port);
        u_input_port.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Username:");
        lblNewLabel_2.setBounds(10, 60, 54, 15);
        contentPanel.add(lblNewLabel_2);

        u_input_username = new JTextField();
        u_input_username.setBounds(74, 57, 350, 21);
        contentPanel.add(u_input_username);
        u_input_username.setColumns(10);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                u_button_ok = new JButton("OK");
                u_button_ok.setActionCommand("OK");
                buttonPane.add(u_button_ok);
                getRootPane().setDefaultButton(u_button_ok);
            }
            {
                u_button_cancel = new JButton("Cancel");
                u_button_cancel.setActionCommand("Cancel");
                buttonPane.add(u_button_cancel);
            }
        }

        u_input_address.setText("127.0.0.1");
        u_input_port.setText("7000");
        u_input_username.setText("Aero");

        u_button_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String address = u_input_address.getText();
                String port = u_input_port.getText();
                String username = u_input_username.getText();
                if (address.equals("") || port.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return ;
                }

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        ClackClientGUI gui = new ClackClientGUI(address, port, username);
                        gui.setVisible(true);
                    }
                });

                dispose();

            }
        });

        u_button_cancel.addActionListener(
                new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        dispose();
                    }
                }
        );

    }
}
