/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testrun;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Leaderboard extends JFrame {

    public Leaderboard() {
        setTitle("Leaderboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window when closed

        // Create panel for leaderboard content
        JPanel leaderboardPanel = new JPanel();
        leaderboardPanel.setLayout(new BorderLayout());

        // Example content: Display top 3 scores
        JLabel headerLabel = new JLabel("Top 3 Scores");
        leaderboardPanel.add(headerLabel, BorderLayout.NORTH);

        JTextArea scoresArea = new JTextArea();
        scoresArea.setEditable(false); // Make it non-editable

        // Fetch data from the database and append it to the JTextArea
        try {
            Connection conn = DriverManager.getConnection("jdbc:derby:LeaderboardDB");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM GameStatistics ORDER BY numberOfMoves ASC FETCH FIRST 3 ROWS ONLY"); // Change the query as needed
            
            int rank = 1;
            while (rs.next()) {
                String username = rs.getString("username");
                int moves = rs.getInt("numberOfMoves");
                scoresArea.append(rank + ". " + username + " - Moves: " + moves + "\n");
                rank++;
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(scoresArea);
        leaderboardPanel.add(scrollPane, BorderLayout.CENTER);

        // Add panel to the frame
        add(leaderboardPanel);

        setLocationRelativeTo(null); // Center the frame on the screen
    }

    // Method to set visibility of the leaderboard
    public void display() {
        setVisible(true);
    }
}
