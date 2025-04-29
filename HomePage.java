import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class HomePage extends JFrame {
    public HomePage() {
        setTitle("Application Homepage");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window

        // Create panels for each block
        JPanel panelMentoring = createBlock("Mentoring");
        JPanel panelAccessResources = createBlock("Access Resources");
        JPanel panelViewResults = createBlock("View Results");

        // Add panels to the frame
        getContentPane().setLayout(new GridLayout(1, 3));
        getContentPane().add(panelMentoring);
        getContentPane().add(panelAccessResources);
        getContentPane().add(panelViewResults);

        // Add action listeners to each block
        panelMentoring.addMouseListener(new BlockMouseListener("Mentoring"));
        panelAccessResources.addMouseListener(new BlockMouseListener("Access Resources"));
        panelViewResults.addMouseListener(new BlockMouseListener("View Results"));
    }

    private JPanel createBlock(String label) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel titleLabel = new JLabel(label);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.CENTER);
        return panel;
    }

    private static class BlockMouseListener extends MouseAdapter {
        private String blockName;

        public BlockMouseListener(String blockName) {
            this.blockName = blockName;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (blockName.equals("Access Resources")) {
                openMentoringApp();
            } else {
                JOptionPane.showMessageDialog(null, "Clicked on: " + blockName);
            }
        }

        private void openMentoringApp() {
            SwingUtilities.invokeLater(() -> {
                Resources mentoringApp = new Resources();
                mentoringApp.setVisible(true);
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomePage homePage = new HomePage();
            homePage.setVisible(true);
        });
    }
}
