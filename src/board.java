/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author nevinkishore
 */
public class board {
    
    private static final Set<Integer> snakes = new HashSet<>();
    private static final Set<Integer> ladders = new HashSet<>();
    
   static {
       //This part of the code tells what position the snakes are at. We picked random positions for this that seemed reasonable
       snakes.add(7);
       snakes.add(41);
       snakes.add(79);
       snakes.add(99);
       
       //This part of the code tells what position the ladders are at. We picked random positions for this that seemed reasonable
       ladders.add(9);
       ladders.add(24);
       ladders.add(66);
       ladders.add(92);
       
   }       
  
   public static boolean snakePosition(int position) {
       return snakes.contains(position);
       
   }
   
   public static boolean ladderPosition(int position) {
       return ladders.contains(position);
   }

     
}
