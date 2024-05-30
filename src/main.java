
import java.util.InputMismatchException;
import java.util.List;
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

    private static boolean turnEnding;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameResults GameResults = new GameResults("record.txt");
        GameResults.loadFile();

        boolean exit = false;
        
        System.out.println("Snakers and Ladders! (CLI Version)\n");
        while (!exit) {
            System.out.println("Please Choose an option");
            System.out.println("1. Start Game");
            System.out.println("2. View Game Results");
            System.out.println("3. Exit");
            System.out.println("\n");
            
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch(InputMismatchException e) {
                System.out.println("\nInvalid input! Please enter a number: ");
                scanner.nextLine();
                continue;
            }
           

            switch (choice) {
                case 1:
                    gameRunning(GameResults, scanner);
                    break;

                case 2:
                    displayGameResults(GameResults);
                    break;

                case 3:
                    exit = true;
                    System.out.println("\nThank you for playing!");
                    break;

                default:
                    System.out.println("\nInvalid option, Please try again. ");

            }
        }
        scanner.close();
    }

    //Core method of the game that involves the dice class and moving positions
    private static void gameRunning(GameResults GameResults, Scanner scanner) {
        System.out.print("Enter username: ");
        String name = scanner.nextLine();

        User user = new User(name);
        Dice dice = new Dice();

        turnEnding = false;

        while (!turnEnding) {
            System.out.println("\n" + user.getName() + "'s turn: ");
            System.out.print(user.getName() + " press 'r' to roll: \n");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("r")) {
                int rollNum = dice.roll();
                System.out.println(user.getName() + " rolled a " + rollNum);

                int newPosition = user.getPosition() + rollNum;
                if (newPosition < 100) {
                    user.setPosition(newPosition);
                    System.out.println(user.getName() + "'s new postion: " + user.getPosition() + "\n");
                    directsSnakeAndLadder(user, newPosition);
                
            } else if(newPosition == 100){
                user.setPosition(newPosition);
                System.out.println("\n GAMEOVER \n");
                System.out.println( user.getName() + " has completed the game! \n");
                System.out.println("Press '1' to Play Again! \n");
                
                turnEnding = true;
            } else {
                System.out.println("Your roll overshot, roll again");
            }
        } else {
                    System.out.println("Invalid Input");
                    }
        GameResults.addScore(user.getName(), user.getNumMoves(), user.getSnakesBitten(), user.getNumLaddersClimbed());

    }
    }
    
    //Method to direct the position of a user in the event that they land on a snake or a ladder
    private static void directsSnakeAndLadder(User user, int newPosition) {
        if (board.snakePosition(newPosition)) {
            System.out.print(user.getName() + " has been bit by a snake! Now moved down\n");
            int moveDown = board.getSnakeDeduction(newPosition);
            user.setPosition(newPosition + moveDown);
            user.snake();
            System.out.println(user.getName() + "'s new position: " + user.getPosition());

        } else if (board.ladderPosition(newPosition)) {
            System.out.println(user.getName() + " climbed a ladder! Moved up");
            int moveUp = board.getLadderAddition(newPosition);
            user.setPosition(newPosition + moveUp);
            user.ladder();
            System.out.println(user.getName() + "'s new position: " + user.getPosition());
        }
    }
    
    //Method to display the game results of the last game played
    private static void displayGameResults(GameResults GameResults) {
        List<ScoreEntry> scores = GameResults.getScores();
        if (!scores.isEmpty()) {
            System.out.println("\nGame Result:");
            
            ScoreEntry lastEntry = scores.get(scores.size() - 1);
               
           
           System.out.println("Player Name: " + lastEntry.getUserName() + "\n" + "Number of Moves: "+ lastEntry.getNumMoves() + "\n" + "Number Of Snakes Bitten: " + lastEntry.getNumSnakesBitten() + "\n" + "Number of Ladders Climbed: " + lastEntry.getNumLaddersClimbed() + "\n");
        } else {
            System.out.println("No Previous Game Result\n");
        }
    }
}

