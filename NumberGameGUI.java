import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGameGUI {
    private JFrame frame;
    private JLabel titleLabel;
    private JLabel instructionLabel;
    private JTextField textField;
    private JButton submitButton;
    private int targetNumber;
    private int attempts;
    private int maxAttempts;
    private int score;

    public NumberGameGUI() {
        frame = new JFrame("Number Guessing Game");
        titleLabel = new JLabel("Welcome to the Number Guessing Game!");
        instructionLabel = new JLabel("Enter your guess (1-100):");
        textField = new JTextField(10);
        submitButton = new JButton("Submit");
        targetNumber = generateRandomNumber();
        maxAttempts = 5;
        attempts = 0;
        score = 0;

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.add(titleLabel);
        panel.add(instructionLabel);
        panel.add(textField);
        panel.add(submitButton);

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    private void checkGuess() {
        int userGuess;

        try {
            userGuess = Integer.parseInt(textField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        attempts++;

        if (userGuess == targetNumber) {
            JOptionPane.showMessageDialog(frame, "Congratulations! You guessed the correct number.", "Success", JOptionPane.INFORMATION_MESSAGE);
            score++;
        } else if (attempts < maxAttempts) {
            String message = (userGuess < targetNumber) ? "Too low! Try a higher number." : "Too high! Try a lower number.";
            JOptionPane.showMessageDialog(frame, message, "Try Again", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Sorry! You've used all your attempts. The correct number was " + targetNumber, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }

        if (attempts < maxAttempts) {
            targetNumber = generateRandomNumber();
            textField.setText("");
        } else {
            showScore();
        }
    }

    private void showScore() {
        JOptionPane.showMessageDialog(frame, "Your final score is: " + score, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        frame.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NumberGameGUI());
    }
}
