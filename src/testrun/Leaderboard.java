/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testrun;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Leaderboard {

    public static void updateUserStats(String username, int numberOfMoves, int snakesBitten, int laddersClimbed) {
        String insertSQL = "INSERT INTO GameStatistics (username, numberOfMoves, snakesBitten, laddersClimbed) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBManager.connect();
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
        String selectSQL = "SELECT username, numberOfMoves, snakesBitten, laddersClimbed FROM GameStatistics";
        try (Connection conn = DBManager.connect();
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
}
