/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testrun;

/**
 *
 * @author nevinkishore
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameBoard extends JFrame {

    private String username;
    private JButton[] tiles = new JButton[100]; // Array to keep track of the tiles
    private int currentPosition = 0; // Start position of the player
    private JLabel positionLabel;

    public GameBoard(String username) {
        this.username = username; // Store the username
        setTitle("Snakes and Ladders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);

        // Set background image of the frame
        setContentPane(new JLabel(new ImageIcon("./resources/background.png"))); // Replace "background.jpg" with your image path
        setLayout(new BorderLayout()); // Use BorderLayout for more flexible positioning

        // Create and add tiles to the board
        JPanel boardPanel = new JPanel();
        boardPanel.setOpaque(false); // Make the panel transparent
        boardPanel.setLayout(new GridLayout(10, 10)); // 10x10 grid layout for the board
        int currentNumber = 100;
        boolean isEvenRow = true;
        for (int i = 0; i < 10; i++) {
            if (isEvenRow) {
                for (int j = 0; j < 10; j++) {
                    JButton tile = new JButton(Integer.toString(currentNumber--));
                    tile.setPreferredSize(new Dimension(35, 35)); // smaller tiles
                    tile.setEnabled(false); // Disable buttons
                    tiles[100 - currentNumber - 1] = tile; // Store reference in array
                    boardPanel.add(tile); // Add buttons to the board panel
                }
            } else {
                for (int j = 9; j >= 0; j--) {
                    JButton tile = new JButton(Integer.toString(currentNumber--));
                    tile.setPreferredSize(new Dimension(35, 35)); // smaller tiles
                    tile.setEnabled(false); // Disable buttons
                    tiles[100 - currentNumber - 1] = tile; // Store reference in array
                    boardPanel.add(tile); // Add buttons to the board panel
                }
            }
            isEvenRow = !isEvenRow;
        }

        // Add the board panel to the center
        add(boardPanel, BorderLayout.CENTER);

        // Create panel for the bottom section
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        // Add username label to the bottom
        JLabel usernameLabel = new JLabel("Welcome, " + username);
        usernameLabel.setOpaque(true); // Make the label opaque to show the background
        usernameLabel.setBackground(Color.WHITE); // Set the background color of the label
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the text
        bottomPanel.add(usernameLabel, BorderLayout.NORTH);

        // Create roll button
        JButton rollButton = new JButton("Roll");
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });
        bottomPanel.add(rollButton, BorderLayout.WEST);

        // Create exit button
        JButton exitBtn = new JButton("Exit");
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        bottomPanel.add(exitBtn, BorderLayout.EAST);

        // Create position label
        positionLabel = new JLabel("Position: 1");
        positionLabel.setOpaque(true); // Make the label opaque
        positionLabel.setBackground(Color.WHITE); // Set the background color of the label
        positionLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the text
        bottomPanel.add(positionLabel, BorderLayout.CENTER);

        // Add bottom panel
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);

        // Highlight the starting position
        highlightCurrentPosition();
    }

    private void rollDice() {
        Random rand = new Random();
        int roll = rand.nextInt(6) + 1; // Roll a 6-sided die
        int newPosition = currentPosition + roll;

        if (newPosition < 100) {
            updatePosition(newPosition);
        } else if (newPosition == 100) {
            updatePosition(newPosition);
            JOptionPane.showMessageDialog(this, "Congratulations, " + username + "! You have completed Snakes and Ladders!");
        }
    }

    private void updatePosition(int newPosition) {
        tiles[currentPosition].setBackground(null); // Revert the current position's tile color
        currentPosition = newPosition;
        highlightCurrentPosition();
        positionLabel.setText("Position: " + (currentPosition + 1));
    }

    private void highlightCurrentPosition() {
        tiles[currentPosition].setBackground(Color.YELLOW);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameBoard gameBoard = new GameBoard("Player1"); // Pass the username here
            gameBoard.setVisible(true);
        });
    }
}
