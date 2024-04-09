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
        
        Scanner scanner = new Scanner(System.in);
       
        System.out.println("Press 'r' to Roll Dice");
        String input = scanner.nextLine();
        
        while(!input.equalsIgnoreCase("r")){
            System.out.println("Error! Input not valid");
            input = scanner.nextLine();
        }
        
        int rollResult = dice.roll();
        System.out.println(name + " rolled: " + rollResult);
        
    }
}
