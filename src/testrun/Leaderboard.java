/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testrun;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Leaderboard {

    private static final String URL = "jdbc:derby://localhost:1527/LeaderboardDB";
    private static final String USER_NAME = "pdc";
    private static final String PASSWORD = "pdc";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }

    private static void createTableIfNotExists() {
        String createTableSQL = "CREATE TABLE GameStats (" +
                "username VARCHAR(255), " +
                "numberOfMoves INT, " +
                "snakesBitten INT, " +
                "laddersClimbed INT)";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createTableSQL);
        } catch (SQLException e) {
            // If the table already exists, do nothing
            if (!e.getSQLState().equals("X0Y32")) {
                e.printStackTrace();
            }
        }
    }

    public static void updateUserStats(String username, int numberOfMoves, int snakesBitten, int laddersClimbed) {
        String insertSQL = "INSERT INTO GameStats (username, numberOfMoves, snakesBitten, laddersClimbed) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, username);
            pstmt.setInt(2, numberOfMoves);
            pstmt.setInt(3, snakesBitten);
            pstmt.setInt(4, laddersClimbed);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readLeaderboard() {
        List<String> leaderboard = new ArrayList<>();
        String selectSQL = "SELECT username, numberOfMoves, snakesBitten, laddersClimbed FROM GameStats ORDER BY numberOfMoves ASC FETCH FIRST 3 ROWS ONLY";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {
            while (rs.next()) {
                String entry = "Username: " + rs.getString("username") +
                        ", Moves: " + rs.getInt("numberOfMoves") +
                        ", Snakes Bitten: " + rs.getInt("snakesBitten") +
                        ", Ladders Climbed: " + rs.getInt("laddersClimbed");
                leaderboard.add(entry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaderboard;
    }

    public static void main(String[] args) {
        createTableIfNotExists();
        List<String> topPlayers = readLeaderboard();

        // Create the GUI
        JFrame frame = new JFrame("Leaderboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.append("Top 3 Players with the Least Moves:\n");

        for (String player : topPlayers) {
            textArea.append(player + "\n");
        }

        frame.add(new JScrollPane(textArea));
        frame.setVisible(true);
    }
}
