package main.client;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

public class ImagePreviewDialog extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JLabel u_image_container;


    /**
     * Create the dialog.
     */
    public ImagePreviewDialog(byte[] buffer) {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        try{
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            BufferedImage bimage = ImageIO.read(bais);
            u_image_container = new JLabel(new ImageIcon(bimage));
            contentPanel.add(u_image_container, BorderLayout.CENTER);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
