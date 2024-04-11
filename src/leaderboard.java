
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
                System.out.println("Player:" + entry.getUserName());
                System.out.println("Number of Moves:" + entry.getNumMoves());
                System.out.println("Number of Snakes Bitten:" + entry.getNumSnakesBitten());  
                System.out.println("Number of Ladders Climbed:" + entry.getNumLaddersClimbed());
                System.out.println("\n");
           }
           
      } else {
            System.out.println("Leaderboard is currently empty. Complete a game to record a score.");
       }
    }
    
       public void saveFile() {
       try(BufferedWriter writer = new BufferedWriter(new FileWriter("./resources/leaderboard.txt"))) {
          for(ScoreEntry save : scores) {
              int totalMoves = save.getNumMoves();
               int totalSnakesBitten = save.getNumSnakesBitten();
               int totalLaddersClimbed = save.getNumLaddersClimbed();
                
               for(ScoreEntry entry : scores) {
                   if(entry.getUserName().equals(save.getUserName())) {
                        totalMoves += entry.getNumMoves();
                        totalSnakesBitten += entry.getNumSnakesBitten();
                        totalLaddersClimbed += entry.getNumLaddersClimbed();
                   }
               }
                
              writer.write("\n" + save.getUserName() + "\nNumber of Moves:" + totalMoves + "\nNumber of Snakes Bitten: " + totalSnakesBitten + "\nNumber of Ladders Climbed: " +  totalLaddersClimbed + "\n\n");
          }
      } catch (IOException ex) {
          Logger.getLogger(leaderboard.class.getName()).log(Level.SEVERE, null, ex);
      } 
        }
        
   public void loadFile() {

       
       try(BufferedReader reader = new BufferedReader(new FileReader("./resources/leaderboard.txt"))) {
           String blank;
            while((blank = reader.readLine()) != null) {
               String[] units = blank.split(",");
               if(units.length == 5) {
               }
               
            }
       
       } catch (FileNotFoundException ex){
           Logger.getLogger(leaderboard.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex) {
            Logger.getLogger(leaderboard.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
   }

