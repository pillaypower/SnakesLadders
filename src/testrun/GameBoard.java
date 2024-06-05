/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testrun;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameBoard extends JFrame {

    private String username;
    private JButton[] tiles = new JButton[100]; // Array to keep track of the tiles
    private int currentPosition = 0; // Start position of the player
    private JLabel positionLabel;

    private Set<Integer> snakes = new HashSet<>();
    private Set<Integer> ladders = new HashSet<>();
    private HashMap<Integer, Integer> snakeDeduction = new HashMap<>();
    private HashMap<Integer, Integer> ladderAddition = new HashMap<>();

    public GameBoard(String username) {
        this.username = username; // Store the username
        setTitle("Snakes and Ladders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);

        // Set background image of the frame
        setContentPane(new JLabel(new ImageIcon("./resources/background.png"))); // Replace "background.jpg" with your image path
        setLayout(new BorderLayout()); // Use BorderLayout for more flexible positioning

        // Initialize snakes and ladders
        initializeSnakesAndLadders();

        // Create and add tiles to the board
        JPanel boardPanel = new JPanel();
        boardPanel.setOpaque(false); // Make the panel transparent
        boardPanel.setLayout(new GridLayout(10, 10)); // 10x10 grid layout for the board
        boolean isEvenRow = true;
        for (int row = 9; row >= 0; row--) {
            if (isEvenRow) {
                for (int col = 0; col < 10; col++) {
                    addTileToBoard(boardPanel, row, col);
                }
            } else {
                for (int col = 9; col >= 0; col--) {
                    addTileToBoard(boardPanel, row, col);
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

    private void addTileToBoard(JPanel boardPanel, int row, int col) {
        int tileNumber = row * 10 + col + 1;
        JButton tile = new JButton(Integer.toString(tileNumber));
        tile.setPreferredSize(new Dimension(35, 35)); // smaller tiles
        tile.setEnabled(false); // Disable buttons
        tiles[tileNumber - 1] = tile; // Store reference in array
        colorTile(tileNumber, tile); // Color the tile if it's a snake or ladder
        boardPanel.add(tile); // Add buttons to the board panel
    }

    private void initializeSnakesAndLadders() {
        // Snakes
        snakes.add(24);
        snakes.add(51);
        snakes.add(75);
        snakes.add(99);

        snakeDeduction.put(24, -10);
        snakeDeduction.put(51, -23);
        snakeDeduction.put(75, -20);
        snakeDeduction.put(99, -9);

        // Ladders
        ladders.add(9);
        ladders.add(39);
        ladders.add(63);
        ladders.add(88);

        ladderAddition.put(9, 9);
        ladderAddition.put(39, 21);
        ladderAddition.put(63, 8);
        ladderAddition.put(88, 4);
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
        tiles[currentPosition].setOpaque(true); // Ensure tile is opaque to show background color
        currentPosition = newPosition;
        checkForSnakesOrLadders();
        highlightCurrentPosition();
        positionLabel.setText("Position: " + (currentPosition + 1));

        if (currentPosition == 99) {
            JOptionPane.showMessageDialog(this, "Congratulations, " + username + "! You have completed Snakes and Ladders!");
        }
    }

    private void checkForSnakesOrLadders() {
        if (snakes.contains(currentPosition)) {
            currentPosition += snakeDeduction.get(currentPosition);
            JOptionPane.showMessageDialog(this, "Oh no! You landed on a snake. Moving down to position: " + (currentPosition + 1));
        } else if (ladders.contains(currentPosition)) {
            currentPosition += ladderAddition.get(currentPosition);
            JOptionPane.showMessageDialog(this, "Great! You landed on a ladder. Moving up to position: " + (currentPosition + 1));
        }
    }

    private void highlightCurrentPosition() {
        tiles[currentPosition].setBackground(Color.YELLOW);
        tiles[currentPosition].setOpaque(true); // Ensure tile is opaque to show background color
    }

    private void colorTile(int position, JButton tile) {
        if (snakes.contains(position)) {
            tile.setBackground(Color.RED);
        } else if (ladders.contains(position)) {
            tile.setBackground(Color.GREEN);
        }
        tile.setOpaque(true); // Ensure tile is opaque to show background color
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameBoard gameBoard = new GameBoard("Player1"); // Pass the username here
            gameBoard.setVisible(true);
        });
    }
}
