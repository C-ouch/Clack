package main.client;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class AudioPreviewDialog extends JDialog {

    private final JPanel contentPanel = new JPanel();

    /**
     * Create the dialog.
     */
    public AudioPreviewDialog(byte[] audioData) {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        JButton u_button_play = new JButton("Play");
        contentPanel.add(u_button_play, BorderLayout.CENTER);


        u_button_play.addActionListener(e -> {
            try {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(new ByteArrayInputStream(audioData));
                Clip audioClip = AudioSystem.getClip();
                audioClip.open(audioStream);
                audioClip.start();

            } catch (UnsupportedAudioFileException ex) {
                System.out.println("The specified audio file is not supported.");
                ex.printStackTrace();
            } catch (LineUnavailableException ex) {
                System.out.println("Audio line for playing back is unavailable.");
                ex.printStackTrace();
            } catch (IOException ex) {
                System.out.println("Error playing the audio file.");
                ex.printStackTrace();
            }
        });

    }

}
