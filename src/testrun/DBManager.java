/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testrun;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

    private static final String URL = "jdbc:derby://localhost:1527/LeaderboardDB";
    private static final String USER_NAME = "pdc";
    private static final String PASSWORD = "pdc";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }

    public static void makeDB() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS GameStatistics ("
                + "id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
                + "username VARCHAR(255) NOT NULL, "
                + "numberOfMoves INTEGER, "
                + "snakesBitten INTEGER, "
                + "laddersClimbed INTEGER)";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Database schema created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating database/");
            e.printStackTrace();
        }
    }
     public static void main(String[] args) {
         makeDB();
//        try {
//
//            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/LeadboardDB", "pdc", "pdc");
//            
//            System.out.println("Database connection successful!");
//            
//      
//            conn.close();
//        } catch (SQLException e) {
//            System.err.println("Database connection failed!");
//            e.printStackTrace();
//        }
    }
}

