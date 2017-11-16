package FrontEnd;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author Rhys
 */
public class AnimationRunner extends Thread {

    private DrawPanel panel;
    private int counter;
    JButton button;

    public AnimationRunner(DrawPanel panel, JButton button) {
        this.panel = panel;
        counter = 0;
        this.button = button;
    }

    @Override
    public void run() {
        //Using if statement to call the text of button to stop animation
        //if (!button.getText().equals("Stop Animation")) {
        while (true) {
            panel.MoveSun(1);
            panel.MoveCloud(1);
            panel.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(AnimationRunner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
