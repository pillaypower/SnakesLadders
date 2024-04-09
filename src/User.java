/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nevinkishore
 */
public class User {
    private String name;
    private int position;
            
    public User(String name) {
        this.name = name;
        this.position = 0;
    }
    
    public String getName() {
        return name;
    }
    
    public int getPosition() {
        return position;
    }
    
    public void setPosition(int position) {
        this.position = position;
    }
    
    public void moveForward(int move) {
        position += move;
    }
    
    public void moveBackward(int move) {
        position -= move;
    }
}
