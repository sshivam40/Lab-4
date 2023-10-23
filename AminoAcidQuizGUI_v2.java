import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AminoAcidQuizGUI_v2 {

    public static String[] SHORT_NAMES = {"A","R", "N", "D", "C", "Q", "E", "G", "H", "I", "L", "K", "M", "F", "P", "S", "T", "W", "Y", "V"};
    public static String[] FULL_NAMES = {"alanine","arginine", "asparagine", "aspartic acid", "cysteine", "glutamine", "glutamic acid", "glycine", "histidine","isoleucine", "leucine", "lysine", "methionine", "phenylalanine", "proline", "serine","threonine","tryptophan", "tyrosine", "valine"};

    private JFrame frame;
    private JLabel questionLabel;
    private JTextField answerField;
    private JLabel timeLabel;
    private JLabel scoreLabel;
    private JButton startButton;
    private JButton cancelButton;
    private Timer timer;
    private int score = 0;
    private int timeLeft = 30;
    private int currentQuestionIndex;

    public AminoAcidQuizGUI_v2() {
        frame = new JFrame("Amino Acid Quiz v2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(6, 1));

        questionLabel = new JLabel();
        frame.add(questionLabel);

        answerField = new JTextField();
        frame.add(answerField);

        timeLabel = new JLabel("Time left: 30 seconds");
        frame.add(timeLabel);

        scoreLabel = new JLabel("Score: 0");
        frame.add(scoreLabel);

        startButton = new JButton("Start Quiz");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startQuiz();
            }
        });
        frame.add(startButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                resetQuiz();
            }
        });
        frame.add(cancelButton);

        frame.setVisible(true);
    }

    private void startQuiz() {
        score = 0;
        timeLeft = 30;

        answerField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                askQuestion();
            }
        });

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeLeft <= 0) {
                    timer.stop();
                    endQuiz();
                } else {
                    timeLeft--;
                    timeLabel.setText("Time left: " + timeLeft + " seconds");
                }
            }
        });

        askQuestion();
        timer.start();
    }

    private void checkAnswer() {
        String answer = answerField.getText().toUpperCase();
        if (answer.equals(SHORT_NAMES[currentQuestionIndex])) {
            score++;
            scoreLabel.setText("Score: " + score);
        }
    }

    private void askQuestion() {
        currentQuestionIndex = (int) (Math.random() * FULL_NAMES.length);
        questionLabel.setText("Enter the one character code for: " + FULL_NAMES[currentQuestionIndex]);
        answerField.setText("");
    }

    private void endQuiz() {
        JOptionPane.showMessageDialog(frame, "Quiz over! Your score: " + score);
        resetQuiz();
    }

    private void resetQuiz() {
        score = 0;
        timeLeft = 30;
        questionLabel.setText("");
        scoreLabel.setText("Score: 0");
        timeLabel.setText("Time left: 30 seconds");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AminoAcidQuizGUI_v2();
            }
        });
    }
}
