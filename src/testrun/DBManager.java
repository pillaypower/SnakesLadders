/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testrun;

/**
 *
 * @author nevinkishore
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBManager {

    private static final String URL = "jdbc:derby://localhost:1527/LeaderboardDB";  
    private static final String USER_NAME = "pdc"; 
    private static final String PASSWORD = "pdc";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void initializeDatabase() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS GameStatistics ("
                + "id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
                + "username VARCHAR(255) NOT NULL, "
                + "numberOfMoves INTEGER, "
                + "snakesBitten INTEGER, "
                + "laddersClimbed INTEGER)";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
