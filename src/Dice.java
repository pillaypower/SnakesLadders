
import java.util.Random;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ishaa
 */
public class Dice {
    
    private Random random;
    
    public Dice(){
        random = new Random();
    }
    
    public int roll(){
        return random.nextInt(6) + 1;
    }
}
