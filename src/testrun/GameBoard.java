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

public class GameBoard extends JFrame {

    public GameBoard() {
        setTitle("Snakes and Ladders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        // Set background image of the frame
        setContentPane(new JLabel(new ImageIcon("./resources/background.png"))); // Replace "background.jpg" with your image path
        setLayout(new GridLayout(10, 10)); // 10x10 grid layout for the board

        // Create and add tiles to the board
        int currentNumber = 100;
        boolean isEvenRow = true;
        for (int i = 0; i < 10; i++) {
            if (isEvenRow) {
                for (int j = 0; j < 10; j++) {
                    JButton tile = new JButton(Integer.toString(currentNumber--));
                    tile.setPreferredSize(new Dimension(35, 35)); // smaller tiles
                    add(tile); // Add buttons directly to the frame
                }
            } else {
                for (int j = 9; j >= 0; j--) {
                    JButton tile = new JButton(Integer.toString(currentNumber--));
                    tile.setPreferredSize(new Dimension(35, 35)); // smaller tiles
                    add(tile); // Add buttons directly to the frame
                }
            }
            isEvenRow = !isEvenRow;
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameBoard());
    }
}
