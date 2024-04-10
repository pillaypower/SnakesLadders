/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author nevinkishore
 */
public class board {
    
    private static final Set<Integer> snakes = new HashSet<>();
    private static final Set<Integer> ladders = new HashSet<>();
    private static final Map<Integer, Integer> snakeDeduction = new HashMap<>();
    private static final Map<Integer, Integer> ladderAddition = new HashMap<>();
    
   static {
       //This part of the code tells what position the snakes are at. We picked random positions for this that seemed reasonable
       snakes.add(24);
       snakes.add(51);
       snakes.add(75);
       snakes.add(99);
       
       //This part of the code tells what position the ladders are at. We picked random positions for this that seemed reasonable
       ladders.add(9);
       ladders.add(39);
       ladders.add(63);
       ladders.add(88);
       
       //This part shows how many spaces you move down when bitten. We picked random positions but reasonable for the game
       snakeDeduction.put(24, -10);
       snakeDeduction.put(51, -23);
       snakeDeduction.put(75, -20);
       snakeDeduction.put(99, -9);
       
       //This part shows how many spaces you move when climbing the ladder. We picked random positions but reasonable for the game
       ladderAddition.put(9, +9);
       ladderAddition.put(39, +21);
       ladderAddition.put(63, +8);
       ladderAddition.put(88, +4);
       
   }   
  
   public static boolean snakePosition(int position) {
       return snakes.contains(position);
       
   }
   
   public static boolean ladderPosition(int position) {
       return ladders.contains(position);
   }
   
   public static int getSnakeDeduction(int position){
       Integer deduction = snakeDeduction.get(position);
       if(deduction != null){
           return deduction;
       }
       else {
           return 0;
       }
       }

   public static int getLadderAddition(int position){
       Integer addition = ladderAddition.get(position);
       if(addition != null){
           return addition;
       }
       else {
           return 0;
       }
       } 
}
