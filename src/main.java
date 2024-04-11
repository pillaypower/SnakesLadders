
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
        Scanner scanner = new Scanner(System.in);
        leaderboard leaderboard = new leaderboard("leaderboard.txt");
        leaderboard.loadFile();

        boolean exit = false;
        System.out.println("Snakers and Ladders! (CLI Version)\n");
        while (!exit) {
            System.out.println("Please Choose an option");
            System.out.println("1. Start Game");
            System.out.println("2. View Leaderboard");
            System.out.println("3. Exit");
            System.out.println("\n");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    gameRunning(leaderboard);
                    break;

                case 2:
                    //where we would display leaderboard method
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

    private static void gameRunning(leaderboard leaderboard) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String name = scanner.nextLine();

        User user = new User(name);
        Dice dice = new Dice();

        while (user.getPosition() < 100) {
            int rollNum = dice.roll();
            System.out.println(user.getName() + " rolled a: " + rollNum);
            int newPosition = user.getPosition() + rollNum;
            if (newPosition <= 100) {
                user.setPosition(newPosition);
                System.out.println(user.getName() + "'s new postion: " + user.getPosition() + "\n");
                if (board.snakePosition(user.getPosition())) {
                    System.out.println(user.getName() + " has been bit by a snake! Moved down");
                    int downward = board.getSnakeDeduction(user.getPosition());
                    user.setPosition(user.getPosition() + downward);
                    user.snake();
                    System.out.println(user.getName() + "'s new position: " + user.getPosition() + "\n");

                } else if (board.ladderPosition(user.getPosition())) {
                    System.out.println(user.getName() + " climbed a ladder! Moved up");
                    int upward = board.getLadderAddition(user.getPosition());
                    user.setPosition(user.getPosition() + upward);
                    user.ladder();
                    System.out.println(user.getName() + "'s new position:" + user.getPosition() + "\n");

                }
            }

        }

        System.out.println("\n" + user.getName() + "has completed the game!");
        System.out.println("Game Over! Thank you for playing!");

        leaderboard.addScore(user.getName(), user.getNumMoves(), user.getSnakesBitten(), user.getNumLaddersClimbed());

    }
}

//    private static void displayLeaderboard(leaderboard leaderboard) {
//        List<ScoreEntry> scores = leaderboard.getScores();
//        if(!scores.isEmpty()) {
//            System.out.println("Leaderboard:");
//            System.out.println("Player Name, Moves, Snakes Bitten, Ladders Climbed");
//            for(ScoreEntry entry : scores) {
//                System.out.println(entry.getName() + entry.getMoves() + entry.get)
//            }
//        
//        }
//    }
//
//    System.out.println (name 
//    + "press 'R' to Roll Dice");
//        String input = scanner.nextLine();
//
//    while(!input.equalsIgnoreCase ( 
//        "r")){
//            System.out.println("Error! Input not valid");
//        input = scanner.nextLine();
//    }
//
//    int rollResult = dice.roll();
//
//    System.out.println (name 
//
//+ " rolled: " + rollResult);
//        
//    }
//    
//    if (Board.snakePosition(position)){
//        System.out.println("Hsssssss! You got bit by a snake!");
//    }
//    
//    if (Board.laddersPosition(position)){
//        System.out.println("Congrats! You climbed a ladder!");
//    }
//}
