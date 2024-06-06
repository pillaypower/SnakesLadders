/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testrun;

/**
 *
 * @author ishaa
 */
public class CLassDataExample {
    
}
 package caloriecounterbasedonoldcuiprogram;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DatabaseIO {
    
    EatenTodayIO day = new EatenTodayIO();
    Scanner inputScanner = new Scanner(System.in);

    public void addFoodToDatabase(String foodName) {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO FoodDatabase (foodName, protein, carbs, calories) VALUES (?, ?, ?, ?)")) {
            System.out.println("Enter the protein (/100g):");
            double protein = inputScanner.nextDouble();

            System.out.println("Enter the carbohydrates (/100g):");
            double carbs = inputScanner.nextDouble();

            System.out.println("Enter the calories (/100g):");
            double calories = inputScanner.nextDouble();

            pstmt.setString(1, foodName);
            pstmt.setDouble(2, protein);
            pstmt.setDouble(3, carbs);
            pstmt.setDouble(4, calories);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Food item added successfully.");
                searchInDatabase(foodName);
            } else {
                System.out.println("Failed to add food item.");
            }
        } catch (SQLException | InputMismatchException e) {
            System.out.println("Error adding food item: " + e.getMessage());
        } finally {
            inputScanner.nextLine(); // Consume newline character
        }
    }

    public void searchInDatabase(String searchTerm) {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM FoodDatabase WHERE name LIKE ?")) {
            pstmt.setString(1, "%" + searchTerm + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String foodName = rs.getString("name");
                    double proteinPer100g = rs.getDouble("protein");
                    double carbsPer100g = rs.getDouble("carbs");
                    double caloriesPer100g = rs.getDouble("calories");

                    System.out.println("Found " + foodName + " in database:");
                    System.out.println(formatOutput(foodName, proteinPer100g, carbsPer100g, caloriesPer100g));

                    portionInGrams(foodName, proteinPer100g, carbsPer100g, caloriesPer100g);
                } else {
                    System.out.println(searchTerm + " not found in the database.");

                    System.out.println("Do you want to add " + searchTerm + " to the database? (y/n)");
                    String addFood = inputScanner.nextLine().toLowerCase();

                    if ("yes".equalsIgnoreCase(addFood) || "y".equalsIgnoreCase(addFood)) {
                        addFoodToDatabase(searchTerm);
                    } else if ("no".equalsIgnoreCase(addFood) || "n".equalsIgnoreCase(addFood)) {
                        System.out.println("Food item not added.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching in database: " + e.getMessage());
        }
    }

    private void portionInGrams(String foodName, double proteinPer100g, double carbsPer100g, double caloriesPer100g) {
        double gramsEaten;

        System.out.println("How much of this (in grams) did you have?");
        gramsEaten = inputScanner.nextDouble();

        double protein = (proteinPer100g * gramsEaten) / 100;
        double carbs = (carbsPer100g * gramsEaten) / 100;
        double calories = (caloriesPer100g * gramsEaten) / 100;

        System.out.println("Changed macros based on " + gramsEaten + " grams eaten:");
        System.out.println("Protein: " + protein + "g");
        System.out.println("Carbs: " + carbs + "g");
        System.out.println("Calories: " + calories + "kcal");

        day.saveToDay(foodName + "," + protein + "," + carbs + "," + calories);
    }

    private String formatOutput(String foodName, double protein, double carbs, double calories) {
        return foodName + "\n- Protein: " + protein + "g\n- Carbs: " + carbs + "g\n- Calories: " + calories + "kcal";
    }
}
