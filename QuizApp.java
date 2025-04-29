import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class QuizApp extends JFrame {

    private JLabel lblQuestion;
    private JRadioButton[] radioButtons;
    private JButton btnNext;
    private ButtonGroup buttonGroup;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private int questionNumber = 1;

    public QuizApp() {
        setTitle("Quiz App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the window

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        getContentPane().add(panel);

        lblQuestion = new JLabel();
        lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblQuestion, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(0, 1));
        panel.add(optionsPanel, BorderLayout.CENTER);

        radioButtons = new JRadioButton[4];
        buttonGroup = new ButtonGroup();
        for (int i = 0; i < radioButtons.length; i++) {
            radioButtons[i] = new JRadioButton();
            optionsPanel.add(radioButtons[i]);
            buttonGroup.add(radioButtons[i]);
        }

        btnNext = new JButton("Next");
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });
        panel.add(btnNext, BorderLayout.SOUTH);

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vishu", "root", "2005");
            preparedStatement = connection.prepareStatement("SELECT question_text, option1, option2, option3, option4, correct_answer FROM questions");
            resultSet = preparedStatement.executeQuery();
            showNextQuestion();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to database.", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
        }
    }

    private void showNextQuestion() {
        try {
            if (resultSet.next()) {
                String question = resultSet.getString("question_text");
                lblQuestion.setText("Question " + questionNumber++ + ": " + question);

                for (int i = 0; i < radioButtons.length; i++) {
                    radioButtons[i].setText(resultSet.getString("option" + (i + 1)));
                    radioButtons[i].setSelected(false);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No more questions.");
                dispose();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to retrieve questions.", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
        }
    }

    private void checkAnswer() {
        String selectedAnswer = "";
        for (int i = 0; i < radioButtons.length; i++) {
            if (radioButtons[i].isSelected()) {
                selectedAnswer = radioButtons[i].getText();
                break;
            }
        }

        try {
            String correctAnswer = resultSet.getString("correct_answer");
            if (selectedAnswer.equalsIgnoreCase(correctAnswer)) {
                JOptionPane.showMessageDialog(this, "Correct!");
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect. The correct answer is: " + correctAnswer);
            }
            showNextQuestion();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to retrieve answer.", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
        }
        buttonGroup.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            QuizApp quizApp = new QuizApp();
            quizApp.setVisible(true);
        });
    }
}
