/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testrun;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;

public class MainBackground extends JPanel {

    public Image image;

    public MainBackground() {
        this.image = new ImageIcon("./resources/background.png").getImage();
    }
    
//test
    /**
     * Draw the background of this panel.
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, null);
    }
}

