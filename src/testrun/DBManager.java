/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testrun;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DBManager {
<<<<<<< HEAD
    private static final String DB_URL = "jdbc:derby:Leader_boardDB;create=true";
    private static final String USER = "pdc";
=======

    private static final String URL = "jdbc:derby://localhost:1527/Leader_boardDB";
    private static final String USER_NAME = "pdc";
>>>>>>> 5b2ec15c71448d1a9a35d5c85b4f9ab03d9dce8f
    private static final String PASSWORD = "pdc";

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Connected to the database.");
                createTablesIfNotExist(conn);
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error connecting to database: " + e.getMessage(),
                    "Database Connection Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private static void createTablesIfNotExist(Connection conn) {
        try {
            if (!doesTableExist(conn, "GameStatistics")) {
                createGameStatisticsTable(conn);
            } else {
                System.out.println("GameStatistics table already exists.");
            }
        } catch (SQLException e) {
            System.out.println("Error checking/creating tables: " + e.getMessage());
        }
    }

    private static boolean doesTableExist(Connection conn, String tableName) throws SQLException {
        DatabaseMetaData dbMetaData = conn.getMetaData();
        try (ResultSet rs = dbMetaData.getTables(null, null, tableName.toUpperCase(), null)) {
            return rs.next();
        }
    }

    private static void createGameStatisticsTable(Connection conn) {
        String createGameStatisticsTable = "CREATE TABLE GameStatistics (" +
                                           "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                                           "username VARCHAR(255) NOT NULL," +
                                           "numberOfMoves INT," +
                                           "snakesBitten INT," +
                                           "laddersClimbed INT)";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createGameStatisticsTable);
            System.out.println("GameStatistics table created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating GameStatistics table: " + e.getMessage());
        }
    }
}
