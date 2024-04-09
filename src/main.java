
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nevinkishore
 */
public class main {
    
    public static void main(String[] args) {
        
      
        //gets the code from the other claseses
        User user = new User();
        Dice dice= new Dice();
        board board = new Board();
        boolean exit = false;
        
        Scanner scanner = new Scanner(System.in);
        
        while(!exit) {
            System.out.println("Snakers and Ladders! (CLI Version)");
            System.out.println("\n");
            System.out.println("Please Choose an option");
            System.out.println("1. Start Game");
            System.out.println("2. View Leaderboard");
            System.out.println("3. Exit");
            
            int option = scanner.nextInt();
            scanner.nextLine();
            
            switch(option) {
                case 1:
                //where we put the play game method
                break;
            
                case 2: 
                //where we would display leaderboard method
                break;
                
                case 3:
                exit = true;
                System.out.println("Thank you for playing!");
                break;
                
                default:
                 System.out.println("Invalid option, Please try again. ");
   
            }
        }
        scanner.close();
        
        
       
        System.out.println( name + "press 'R' to Roll Dice");
        String input = scanner.nextLine();
        
        while(!input.equalsIgnoreCase("r")){
            System.out.println("Error! Input not valid");
            input = scanner.nextLine();
        }
        
        int rollResult = dice.roll();
        System.out.println(name + " rolled: " + rollResult);
        
    }
}

import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nevinkishore
 */
public class main {
    
    public static void main(String[] args) {
        //gets the code from the other claseses
        User user = new User();
        Dice dice= new Dice();
        board board = new Board();
        
        //introduction part of the code
        boolean exit = false;
      
        while(!exit) {
            System.out.println("Snakers and Ladders! (CLI Version)");
            System.out.println("\n");
            System.out.println("Please Choose an option");
            System.out.println("1. Start Game");
            System.out.println("2. View Leaderboard");
            System.out.println("3. Exit");
            
            int option = scanner.nextInt();
            scanner.nextLine();
            
            switch(option) {
                case 1:
                //where we put the play game method
                break;
            
                case 2: 
                //where we would display leaderboard method
                break;
                
                case 3:
                exit = true;
                System.out.println("Thank you for playing!");
                break;
                
                default:
                 System.out.println("Invalid option, Please try again. ");
   
            }
        }
        scanner.close(); 
        
        Scanner scanner = new Scanner(System.in);
       
        System.out.println( name + "press 'R' to Roll Dice");
        String input = scanner.nextLine();
        
        while(!input.equalsIgnoreCase("r")){
            System.out.println("Error! Input not valid");
            input = scanner.nextLine();
        }
        
        int rollResult = dice.roll();
        System.out.println(name + " rolled: " + rollResult);
        
    }
}
