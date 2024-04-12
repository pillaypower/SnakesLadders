/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ishaa
 */
public abstract class GameEntity {
    
    protected String name;
    protected int position;
    
    public GameEntity(String name){
        this.name = name;
        this.position = 0;
    }
    
    public String getName(){
        return name;
    }
    
    public int getPosition(){
        return position;
    }
    
    public void setPosition(int position){
        this.position = position;
    }
    
    public abstract void move(board board, Dice dice);
}
