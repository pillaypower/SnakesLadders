import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    public class GameResults {
    private List<ScoreEntry> scores;
    private String fileName;
    
    public GameResults(String filename) {
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
        scores.clear();
      if(!scores.isEmpty()) {
           System.out.println("Game Results: ");
           for(ScoreEntry entry : scores) {
                System.out.println("Player:" + entry.getUserName());
                System.out.println("Number of Moves:" + entry.getNumMoves());
                System.out.println("Number of Snakes Bitten:" + entry.getNumSnakesBitten());  
                System.out.println("Number of Ladders Climbed:" + entry.getNumLaddersClimbed());
                System.out.println("\n");
           }
           
      } else {
            System.out.println("GameResults is currently empty. Complete a game to show results.");
       }
    }
    
       public void saveFile() {
       try(BufferedWriter writer = new BufferedWriter(new FileWriter("./resources/record.txt"))) {
          for(ScoreEntry save : scores) {
              writer.write("\n" + save.getUserName() + "\nNumber of Moves: " + save.getNumMoves() + "\nNumber of Snakes Bitten: " + save.getNumSnakesBitten() + "\nNumber of Ladders Climbed: " + save.getNumLaddersClimbed() + "\n\n");
          
//         
          }
          
         
      } catch (IOException ex) {
          Logger.getLogger(GameResults.class.getName()).log(Level.SEVERE, null, ex);
      } 
        }
        
   public void loadFile() {

       
       try(BufferedReader reader = new BufferedReader(new FileReader("./resources/record.txt"))) {
           String blank;
            while((blank = reader.readLine()) != null) {
               String[] units = blank.split(",");
               if(units.length == 5) {
               }
               
            }
       
       } catch (FileNotFoundException ex){
           Logger.getLogger(GameResults.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex) {
            Logger.getLogger(GameResults.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
   }
  

