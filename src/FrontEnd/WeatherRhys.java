
package FrontEnd;

import Backend.Database;
import Backend.WeatherHistory;

/**
 *
 * @author Rhys
 */
public class WeatherRhys {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Database db = new WeatherHistory();

        GUI gui = new GUI();
        gui.setDB(db);
        gui.showGUI();
    }

}
