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

public class GameBoard extends JFrame {

    private String username;

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
                    boardPanel.add(tile); // Add buttons to the board panel
                }
            } else {
                for (int j = 9; j >= 0; j--) {
                    JButton tile = new JButton(Integer.toString(currentNumber--));
                    tile.setPreferredSize(new Dimension(35, 35)); // smaller tiles
                    boardPanel.add(tile); // Add buttons to the board panel
                }
            }
            isEvenRow = !isEvenRow;
        }

        // Add the board panel to the center
        add(boardPanel, BorderLayout.CENTER);
        
        //creat panel for bottom section
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        
        // Add username label to the bottom
        JLabel usernameLabel = new JLabel("Welcome, " + username);
        usernameLabel.setOpaque(true); // Make the label opaque to show the background
        usernameLabel.setBackground(Color.WHITE); // Set the background color of the label
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the text
        add(usernameLabel, BorderLayout.CENTER);
        
        //exit button
        JButton exitBtn = new JButton("Exit");
        exitBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        
        bottomPanel.add(exitBtn, BorderLayout.EAST);
        
        //Add bottom panel
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameBoard gameBoard = new GameBoard("Player1"); // Pass the username here
            gameBoard.setVisible(true);
        });
    }
}
