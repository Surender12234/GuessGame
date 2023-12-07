package org.guess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessGame extends JFrame {

    private JTextField guessField;
    private JButton guessButton;
    private JTextArea resultArea;

    private int targetNumber;
    private int maxAttempts = 10;
    private int attempts = 0;
    private int score = 0;

    public GuessGame() {
        super("Number Guessing Game");

        targetNumber = generateRandomNumber();

        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        setLayout(new FlowLayout());

        add(new JLabel("Enter your guess: "));
        add(guessField);
        add(guessButton);
        add(new JScrollPane(resultArea));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    private void checkGuess() {
        if (attempts < maxAttempts) {
            int userGuess = Integer.parseInt(guessField.getText());
            attempts++;

            if (userGuess == targetNumber) {
                resultArea.append("Congratulations! You guessed the correct number in " + attempts + " attempts.\n");
                score += maxAttempts - attempts + 1;
                resetGame();
            } else if (userGuess < targetNumber) {
                resultArea.append("Too low! Try again.\n");
            } else {
                resultArea.append("Too high! Try again.\n");
            }

            guessField.setText("");
        } else {
            resultArea.append("Sorry, you've reached the maximum number of attempts. The correct number was: " + targetNumber + "\n");
            resetGame();
        }
    }

    private void resetGame() {
        targetNumber = generateRandomNumber();
        attempts = 0;

        resultArea.append("New game started. I've selected a new number between 1 and 100. Can you guess it?\n");

        resultArea.append("Do you want to play again? Your total score is: " + score + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuessGame();
            }
        });
    }
}
