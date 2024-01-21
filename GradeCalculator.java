import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCalculator extends JFrame implements ActionListener {
    private JLabel[] labels;
    private JTextField[] textFields;
    private JButton calculateButton;
    private JButton clearButton;
    private JLabel resultLabel;

    public GradeCalculator() {
        labels = new JLabel[5];
        textFields = new JTextField[5];

        String[] subjects = {"Subject 1", "Subject 2", "Subject 3", "Subject 4", "Subject 5"};

        for (int i = 0; i < 5; i++) {
            labels[i] = new JLabel(subjects[i]);
            textFields[i] = new JTextField();
            labels[i].setBounds(50, 50 + i * 50, 100, 30);
            textFields[i].setBounds(160, 50 + i * 50, 100, 30);
            add(labels[i]);
            add(textFields[i]);
        }

        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(50, 300, 100, 30);
        calculateButton.addActionListener(this);
        add(calculateButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(160, 300, 100, 30);
        clearButton.addActionListener(this);
        add(clearButton);

        resultLabel = new JLabel();
        resultLabel.setBounds(50, 350, 250, 30);
        add(resultLabel);

        setTitle("Student Grade Calculator");
        setSize(300, 450);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            calculateGrade();
        } else if (e.getSource() == clearButton) {
            clearFields();
        }
    }

    private void calculateGrade() {
        int totalMarks = 0;
        int numSubjects = 0;

        for (int i = 0; i < 5; i++) {
            String marksText = textFields[i].getText();
            if (!marksText.isEmpty()) {
                int marks = Integer.parseInt(marksText);
                totalMarks += marks;
                numSubjects++;
            }
        }

        if (numSubjects == 0) {
            resultLabel.setText("Please enter marks for at least one subject.");
            return;
        }

        double averagePercentage = (double) totalMarks / (numSubjects * 100) * 100;
        String grade = calculateGradeLabel(averagePercentage);

        resultLabel.setText("Total Marks: " + totalMarks +
                " | Average Percentage: " + String.format("%.2f", averagePercentage) + "%" +
                " | Grade: " + grade);
    }

    private void clearFields() {
        for (int i = 0; i < 5; i++) {
            textFields[i].setText("");
        }
        resultLabel.setText("");
    }

    private String calculateGradeLabel(double percentage) {
        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        new GradeCalculator();
    }
}
