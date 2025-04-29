import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Resources extends JFrame {
    public Resources() {
        setTitle("Resources");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JButton resourcesButton = new JButton("Online Resources");
        resourcesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open online resources page
                openOnlineResources();
            }
        });

        JButton videosButton = new JButton("YouTube Videos");
        videosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open YouTube videos page
                openYouTubeVideos();
            }
        });

        panel.add(resourcesButton);
        panel.add(videosButton);

        add(panel);
        setVisible(true);
    }

    private void openOnlineResources() {
        // Open a web browser with online resources page
        String url = "https://www.geeksforgeeks.org/java/";
        try {
            Desktop.getDesktop().browse(java.net.URI.create(url));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error opening online resources: " + e.getMessage());
        }
    }

    private void openYouTubeVideos() {
        // Open a web browser with YouTube videos page
        String url = "https://youtu.be/bm0OyhwFDuY?si=svciTRMXk13MrIoD";
        try {
            Desktop.getDesktop().browse(java.net.URI.create(url));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error opening YouTube videos: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Resources();
            }
        });
    }
}
