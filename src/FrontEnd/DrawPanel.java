
package FrontEnd;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Rhys
 */
public class DrawPanel extends JPanel {

    private int sunX = 500;
    private int sunY = 400;
    private int skyR;
    private int skyG;
    private int skyB;
    private Color skyColor;
    //private Cloud cloud1;
    private int cloud1X = 5;
    private int cloud1Y = 100;
    private int cloud2X = -200;
    private int cloud2Y = 130;


    public DrawPanel() {
        skyR = 0;
        skyG = 0;
        skyB = 0;
        skyColor = new Color(skyR, skyG, skyB);
    }

    @Override
    public void paintComponent(Graphics page) {

        super.paintComponent(page);

        //Set Background
        setBackground(new Color(skyR, skyG, skyB));

        //Draw Sun
        Graphics2D pencil = (Graphics2D) page;
        pencil.setColor(Color.ORANGE);
        pencil.fillOval(sunX, sunY, 60, 60);

        //Draw Grass
        pencil.setColor(new Color(35, 130, 5));
        pencil.fillRect(0, 450, 700, 150);

        //Draw Clouds
        pencil.setColor(Color.white);
        pencil.fillOval(cloud1X, cloud1Y, 50, 60);
        pencil.fillOval(cloud1X + 15, cloud1Y - 25, 70, 80);
        pencil.fillOval(cloud1X + 30, cloud1Y + 30, 70, 50);
        pencil.fillOval(cloud1X + 60, cloud1Y, 80, 60);
        pencil.fillOval(cloud1X + 50, cloud1Y - 30, 60, 40);
        pencil.fillOval(cloud1X + 80, cloud1Y - 20, 70, 60);
        pencil.fillOval(cloud1X + 80, cloud1Y + 20, 70, 60);
        pencil.fillOval(cloud1X + 100, cloud1Y, 70, 60);
        
        pencil.setColor(Color.white);
        pencil.fillOval(cloud2X, cloud1Y, 50, 60);
        pencil.fillOval(cloud2X + 15, cloud2Y - 25, 70, 80);
        pencil.fillOval(cloud2X + 30, cloud2Y + 30, 70, 50);
        pencil.fillOval(cloud2X + 60, cloud2Y, 80, 60);
        pencil.fillOval(cloud2X + 50, cloud2Y - 30, 60, 40);
        pencil.fillOval(cloud2X + 80, cloud2Y - 20, 70, 60);
        pencil.fillOval(cloud2X + 80, cloud2Y + 20, 70, 60);
        pencil.fillOval(cloud2X + 100, cloud2Y, 70, 60);
    }

    public void MoveCloud(int amount) {
        cloud1X += amount;
        cloud2X += amount;
        if(cloud1X == 800){
            cloud1X = -200;
        }
        if(cloud2X == 800){
            cloud2X = -200;
        }
    }

    public void MoveSun(int amount) {
        if (sunY > 50) {
            this.sunY -= amount;

            if (skyR < 200) {
                skyR++;
            }
            if (skyG < 230) {
                skyG++;
            }
            if (skyB < 250) {
                skyB++;
            }
        }
    }
}
