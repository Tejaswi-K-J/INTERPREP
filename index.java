import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

public class index {

    static int points = 0;
    static JFrame frame;
    static JPanel panel;
    static JLabel questionLabel;
    static JLabel questionText;
    static ButtonGroup buttonGroup;
    static JRadioButton optionA;
    static JRadioButton optionB;
    static JRadioButton optionC;
    static JRadioButton optionD;
    static JButton submitButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(index ::initializeUI);
    }

    public static void initializeUI() {
        frame = new JFrame("Quiz App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        frame.add(panel, BorderLayout.CENTER);

        showQuestion1();

        frame.setVisible(true);
    }

    public static void showQuestion1() {
        setupQuestion("Question 1 of 2:", "What is a correct syntax to output \"Hello World\" in Java?",
                new String[]{"A. echo(\"Hello World\")", "B. Console.WriteLine(\"Hello World\")",
                        "C. System.out.println(\"Hello World\")", "D. print(\"Hello World\")"},
                () -> {
                    if (optionC.isSelected()) points++;
                    showQuestion2();
                });
    }

    public static void showQuestion2() {
        setupQuestion("Question 2 of 2:", "Java is short for \"Javascript\".",
                new String[]{"A. true", "B. false", "C. All of the above.", "D. None of the above."},
                () -> {
                    if (optionB.isSelected()) points++;
                    showResult();
                });
    }

    public static void setupQuestion(String questionNumber, String question, String[] options, Runnable onSubmit) {
        panel.removeAll();

        questionLabel = new JLabel(questionNumber);
        questionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Set font size
        panel.add(questionLabel);

        questionText = new JLabel(question);
        questionText.setAlignmentX(Component.LEFT_ALIGNMENT);
        questionText.setFont(new Font("Arial", Font.PLAIN, 16)); // Set font size
        panel.add(questionText);

        buttonGroup = new ButtonGroup();

        optionA = new JRadioButton(options[0]);
        optionA.setAlignmentX(Component.LEFT_ALIGNMENT);
        optionA.setFont(new Font("Arial", Font.PLAIN, 16)); // Set font size
        panel.add(optionA);
        buttonGroup.add(optionA);

        optionB = new JRadioButton(options[1]);
        optionB.setAlignmentX(Component.LEFT_ALIGNMENT);
        optionB.setFont(new Font("Arial", Font.PLAIN, 16)); // Set font size
        panel.add(optionB);
        buttonGroup.add(optionB);

        optionC = new JRadioButton(options[2]);
        optionC.setAlignmentX(Component.LEFT_ALIGNMENT);
        optionC.setFont(new Font("Arial", Font.PLAIN, 16)); // Set font size
        panel.add(optionC);
        buttonGroup.add(optionC);

        optionD = new JRadioButton(options[3]);
        optionD.setAlignmentX(Component.LEFT_ALIGNMENT);
        optionD.setFont(new Font("Arial", Font.PLAIN, 16)); // Set font size
        panel.add(optionD);
        buttonGroup.add(optionD);

        submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        submitButton.setFont(new Font("Arial", Font.BOLD, 16)); // Set font size
        submitButton.setBackground(new Color(52, 152, 219));
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(e -> {
            onSubmit.run();
        });
        panel.add(submitButton);

        frame.revalidate();
        frame.repaint();
    }

    public static void showResult() {
        panel.removeAll();

        JLabel resultLabel = new JLabel("Result:");
        resultLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Set font size
        panel.add(resultLabel);

        JLabel scoreLabel = new JLabel("Total Score: " + points);
        scoreLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Set font size
        panel.add(scoreLabel);

        JLabel messageLabel;
        if (points == 2) {
            messageLabel = new JLabel("Good job!");
        } else if (points >= 1) {
            messageLabel = new JLabel("You Passed the quiz.");
        } else {
            messageLabel = new JLabel("You can still study more for the next quiz.");
        }
        messageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Set font size
        panel.add(messageLabel);

        JButton closeButton = new JButton("Close");
        closeButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        closeButton.setFont(new Font("Arial", Font.BOLD, 16)); // Set font size
        closeButton.setBackground(new Color(231, 76, 60));
        closeButton.setForeground(Color.WHITE);
        closeButton.addActionListener(e -> {
            frame.dispose();
        });
        panel.add(closeButton);

        frame.revalidate();
        frame.repaint();
    }
}
