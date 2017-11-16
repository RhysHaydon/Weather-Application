/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Owner
 */

public class Cloud {
}


/* Unused Class for the Cloud, couldn't get it to work properly

   
    private int xPos;
    private int yPos;
    
    public Cloud(int x, int y){
        xPos = x;
        yPos = y;
    }
    
      public void makeCloud(Graphics page) {
          
        Graphics2D pencil = (Graphics2D) page;
        pencil.setColor(Color.white);
        pencil.fillOval(xPos, yPos, 50, 60);
        pencil.fillOval(xPos + 15, yPos - 25, 70, 80);
        pencil.fillOval(xPos + 30, yPos + 30, 70, 50);
        pencil.fillOval(xPos + 60, yPos, 80, 60);
        pencil.fillOval(xPos + 50, yPos - 30, 60, 40);
        pencil.fillOval(xPos + 80, yPos - 20, 70, 60);
        pencil.fillOval(xPos + 80, yPos + 20, 70, 60);
        pencil.fillOval(xPos + 100, yPos, 70, 60);
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }   
}
*/
