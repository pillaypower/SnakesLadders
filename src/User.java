/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nevinkishoree
 */
public class User extends GameEntity {
    private int numMoves;
    private int numSnakesBitten;
    private int numLaddersClimbed;
            
    public User(String name) {
        super(name);
        this.numMoves = 0;
        this.numLaddersClimbed = 0;
        this.numSnakesBitten = 0;
    }
    
    public int getNumMoves(){
        return numMoves;
    }
     public void setPosition(int position) {
        this.position = position;
        numMoves++;
     }
   public int getNumLaddersClimbed(){
        return numLaddersClimbed;
    }

     public int getSnakesBitten() {
       return numSnakesBitten;
   }
     

   @Override
   public void move(board board, Dice dice){
       int rollNum = dice.roll();
       numMoves++;
       
      
      if(board.snakePosition(getPosition())) {
          snake();
   }
      if(board.ladderPosition(getPosition())) {
          ladder();
   
      }
   }
   public void snake() {
       numSnakesBitten++;
   }
   public void ladder() {
       numLaddersClimbed++;
   }
}
       



