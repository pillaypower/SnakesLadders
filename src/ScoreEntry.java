/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nevinkishore
 */
public class ScoreEntry {
    private String userName;
    private int numMoves;
    private int numSnakesBitten;
    private int numLaddersClimbed;
    
    public ScoreEntry(String userName, int numMoves, int numSnakesBitten, int numLaddersClimbed) {
    this.userName = userName;
    this.numMoves = numMoves;
    this.numSnakesBitten = numSnakesBitten;
    this.numLaddersClimbed = numLaddersClimbed;
}
    public String getUserName() {
        return userName;
    }
    
    public int getNumMoves() {
        return numMoves;
    }
    public int getNumSnakesBitten() {
        return numSnakesBitten;
    }
    public int getNumLaddersClimbed() {
        return numLaddersClimbed;
    }
}
    
