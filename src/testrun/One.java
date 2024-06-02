/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testrun;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;

public class One extends JPanel {

    public Image image;

    public One() {
        this.image = new ImageIcon("./resources/background4.png").getImage();
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

