/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nevinkishore
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
    
   public int getNumLaddersClimbed(){
        return numLaddersClimbed;
    }

     public int getNumSnakeBitten() {
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
       
     //  int newPosition = getPosition() + rollNum;
    //   setPosition(newPosition);
       
      // if(board.snakePosition(newPosition)){
     //      numSnakesBitten++;
        //   int moveDown = board.getSnakeDeduction(newPosition);
      //     setPosition(newPosition + moveDown);
    //   } else if (board.ladderPosition(newPosition)){
  //         numLaddersClimbed++;
      //     int moveUp = board.getLadderAddition(newPosition);
    //       setPosition(newPosition + moveUp);
  // }

       
    
  //  public int getSnakesBitten(){
 //       return numSnakesBitten;
   // }
    
    
  //  public void setPosition(int position) {
       // this.position = position;
     //   numMoves++;
   // }
    
   // public void snake() {
      //  numSnakesBitten++;
    //}
    
  // public void ladder() {
    //   numLaddersClimbed++;
  // }
   
  // public int getSnake() {
   //    return numSnakesBitten;
   //}
   
  // public int getLadder() {
  //     return numLaddersClimbed;
//   }
   
    
//}


