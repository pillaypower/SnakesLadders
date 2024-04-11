
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
//public class leaderboard {
//    private List<ScoreEntry> scores;
//    private String fileName;
//    
//    public leaderboard(String filename) {
//        this.fileName = filename;
//        scores = new ArrayList<>();
//    }
//    
//    
//    public void addScore(String userName, int numMoves, int numSnakesBitten, int numLaddersClimbed) {
//        scores.add(new ScoreEntry(userName, numMoves, numSnakesBitten, numLaddersClimbed));
//       saveFile();
//    }
//    
//    public List<ScoreEntry> getScores() {
//       return scores;
//    }
//    
//    public void displayLeaderboard() {
//      if(!scores.isEmpty()) {
//           System.out.println("Leaderboard:");
//           for(ScoreEntry entry : scores) {
//                System.out.println("Player:" + entry.getUserName());
//                System.out.println("Number of Moves:" + entry.getNumMoves());
//                System.out.println("Number of Snakes Bitten:" + entry.getNumSnakesBitten());  
//                System.out.println("Number of Ladders Climbed:" + entry.getNumLaddersClimbed());
//                System.out.println("\n");
//           }
//           
//      } else {
//            System.out.println("Leaderboard is currently empty. Complete a game to record a score.");
//       }
//    }
//    
//       public void saveFile() {
//       try(BufferedWriter writer = new BufferedWriter(new FileWriter("./resources/leaderboard.txt"))) {
//          for(ScoreEntry save : scores) {
//              int totalMoves = save.getNumMoves();
//               int totalSnakesBitten = save.getNumSnakesBitten();
//               int totalLaddersClimbed = save.getNumLaddersClimbed();
//                
//               for(ScoreEntry entry : scores) {
//                   if(entry.getUserName().equals(save.getUserName())) {
//                        totalMoves += entry.getNumMoves();
//                        totalSnakesBitten += entry.getNumSnakesBitten();
//                        totalLaddersClimbed += entry.getNumLaddersClimbed();
//                   }
//               }
//                
//              writer.write("\n" + save.getUserName() + "\nNumber of Moves:" + totalMoves + "\nNumber of Snakes Bitten: " + totalSnakesBitten + "\nNumber of Ladders Climbed: " +  totalLaddersClimbed + "\n\n");
//          }
//      } catch (IOException ex) {
//          Logger.getLogger(leaderboard.class.getName()).log(Level.SEVERE, null, ex);
//      } 
//        }
//        
//   public void loadFile() {
//       scores.clear();
//       
//       try(BufferedReader reader = new BufferedReader(new FileReader("./resources/leaderboard.txt"))) {
//           String blank;
//            while((blank = reader.readLine()) != null) {
//               String[] units = blank.split(",");
//               if(units.length == 5) {
//               }
//               
//            }
//       
//       } catch (FileNotFoundException ex){
//           Logger.getLogger(leaderboard.class.getName()).log(Level.SEVERE, null, ex);
//       } catch (FileNotFoundException ex){
//           Logger.getLogger(leaderboard.class.getName()).log(Level.SEVERE, null, ex);
//       }
//       }
//   }

-------------------------------------------------------------------------------------------------------------------
             
 NEW CODE***


public class leaderboard {
    
    private Map<String, ScoreEntry> userScores;
    private String fileName;
    
    public Leaderboard(String filename){
        this.fileName = filename;
        userScores = new HashMap<>();
        loadFile();
    }
    
    public void addScore(String userName, int numMoves, int numSnakesBitten, int numLaddersClimbed){
        ScoreEntry scoreEntry = userScores.getOrDefault(userName, new ScoreEntry(userName));
        scoreEntry.updateStrats(numMoves, numSnakesBitten, numLaddersClimbed);
        userScores.put(userName, scoreEntry);
        saveFile();
    }
    
    public void displayLeaderboard(){
        if (!userScores.isEmpty()){
            System.out.println("Leaderboard: ");
            for(ScoreEntry entry : userScores.values()){
            System.out.println("Player:" + entry.getUserName());
                System.out.println("Number of Moves:" + entry.getNumMoves());
                System.out.println("Number of Snakes Bitten:" + entry.getNumSnakesBitten());  
                System.out.println("Number of Ladders Climbed:" + entry.getNumLaddersClimbed());
                System.out.println("\n");
        }
        }else{
            System.out.println("Leaderboard is currently empty");
        }
    }
    
    public void saveFile(){
        String filePath = ("./resources/leaderboard.txt\");
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))){
            for (ScoreEntry entry : userScores.values()){
                 writer.write(entry.getUserName() + "," + entry.getNumMoves() + "," + entry.getNumSnakesBitten() 
                 + "," + entry.getNumLaddersClimbed() + "\n" );
            }
        }
        catch (IOException ex) {
            System.err.println("Error");
        }
    }
    
    public void loadFile(){
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                if (parts.length == 4){
                    String userName = parts[0];
                    int numMoves = Integer.parseInt(parts[1]);
                    int numSnakesBitten = Integer.parseInt(parts[2]);
                    int numLaddersClimbed = Integer.parseInt(parts[3]);
                    ScoreEntry entry = new ScoreEntry(userName, numMoves, numSnakesBitten, numLaddersClimbed);
                    userScores.put(userName, entry);
                }
            }
        }
    } catch (FileNotFoundException ex){
    System.err.println("File not found" );
} catch (IOException ex){
    System.err.println("erri");
                }
            }
