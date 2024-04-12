
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nevinkishore
 */
public class leaderboard {
    private List<ScoreEntry> scores;
    private String fileName;
    
    public leaderboard(String filename) {
        this.fileName = filename;
        scores = new ArrayList<>();
    }
    
    
    public void addScore(String userName, int numMoves, int numSnakesBitten, int numLaddersClimbed) {
        scores.add(new ScoreEntry(userName, numMoves, numSnakesBitten, numLaddersClimbed));
       saveFile();
    }
    
    public List<ScoreEntry> getScores() {
       return scores;
    }
    
    public void displayLeaderboard() {
      if(!scores.isEmpty()) {
           System.out.println("Leaderboard:");
           for(ScoreEntry entry : scores) {
                System.out.println("Player: " + entry.getUserName());
                System.out.println("Number of Moves: " + entry.getNumMoves());
                System.out.println("Number of Snakes Bitten: " + entry.getNumSnakesBitten());  
                System.out.println("Number of Ladders Climbed: " + entry.getNumLaddersClimbed());
                System.out.println("\n");
           }
           
      } else {
            System.out.println("Leaderboard is currently empty. Complete a game to record a score.");
       }
    }
    
       public void saveFile() {
       try(BufferedWriter writer = new BufferedWriter(new FileWriter("./resources/leaderboard.txt"))) {
          for(ScoreEntry save : scores) {
              writer.write(" \n Username: " + save.getUserName() + "\n "
              + "Number of Moves Made: " + save.getNumMoves() + "\n"
               + "Number of Snakes Bitten by: " + save.getNumSnakesBitten() + "\n"
               + "Number of Ladders Climbed: " + save.getNumLaddersClimbed() + "\n");
          }
       }
       catch (IOException ex){
           Logger.getLogger(leaderboard.class.getName()).log(Level.SEVERE, null, ex);
       }
       }
             
  
        
   public void loadFile() {
       scores.clear();
       
       try(BufferedReader reader = new BufferedReader(new FileReader("./resources/leaderboard.txt"))) {
           
           String line;
            while((line = reader.readLine()) != null) {
               String[] units = line.split(",");
               if(units.length == 4) {
               String userName = units[0];
               int numMoves = Integer.parseInt(units[1]);
               int numSnakesBitten = Integer.parseInt(units[2]);
               int numLaddersClimbed = Integer.parseInt(units[3]);
               scores.add(new ScoreEntry(userName,numMoves, numSnakesBitten, numLaddersClimbed));
               }
               
            }
       }
       
       catch (FileNotFoundException ex){
           Logger.getLogger(leaderboard.class.getName()).log(Level.SEVERE, null, ex);
       }
        catch (IOException ex){
           Logger.getLogger(leaderboard.class.getName()).log(Level.SEVERE, null, ex);
       }
       }
}

