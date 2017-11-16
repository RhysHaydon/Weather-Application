package Backend;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rhys
 */

public class WeatherHistory implements Serializable, Database {

    ArrayList<WeatherObservation> history = new ArrayList<>();

    public WeatherHistory() {
    }

    @Override
    public void loadObservationsFromHTMLFile() {

        try {

            Document document = Jsoup.connect("http://rengland.spinetail.cdu.edu.au/observations/").get();

            Elements entries = document.getElementsByTag("tr");

            for (int i = 1; i < entries.size(); i++) {
                Element entry = entries.get(i);
                String place = entry.child(0).text();
                String date = entry.child(1).text();
                Double temperature = Double.parseDouble(entry.child(2).text());
                Double humidity = Double.parseDouble(entry.child(3).text());
                Double uvIndex = Double.parseDouble(entry.child(4).text());
                Double windSpeed = Double.parseDouble(entry.child(5).text());

                WeatherObservation obs = new WeatherObservation(place, date, temperature, humidity, uvIndex, windSpeed);
                this.history.add(obs);
            }
        } catch (IOException ex) {
            Logger.getLogger(WeatherHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getObservations() {
        ArrayList<String> temp = new ArrayList<>();
        for (WeatherObservation wo : this.history) {
            temp.add(wo.formattedString());
        }
        return String.join("#", temp);
    }
    
    @Override
    public String checkWeatherByDate(String userDate){
        String date = null;
        Date newDate;
        for(int i = 0; i < history.size();i++){
            newDate = history.get(i).getDate();
            date = newDate.toString();
        }
        return date;
    }

    @Override
    public String toString() {
        return getObservations();
    }
}

    /*
    //Binary Tree not Operational
    
    private Node root;
    
    
    @Override
    public String getObservations(){
        String allData =  root.print(root);
        return allData;
    }
    

    public void add(int data) {
        if (root == null) {
            root = new Node(data);
        } else {
            insert(root, obs);
        }
    }

    private Node insert(Node node, WeatherObservation obs) {
        if (node == null) {
            node = new Node(obs);
        } else {
            if (obs < node.getData()) {
                node.left = insert(node.left, obs);
            } else {
                node.right = insert(node.right, obs);
            }
        }
        return node;
    }

    public void printTree() {
        if (root != null) {
            print(root);
        } else {
            System.out.println("The tree is empty");
        }
    }

    private void print(Node root) {
        if (root != null) {
            print(root.getLeft());
            System.out.println(root.getData());
            print(root.getRight());
        }
    }

    private class Node {

        public WeatherObservation obs;
        public Node left;
        public Node right;

        public Node(WeatherObservation data) {
            this.obs = data;
        }

        public WeatherObservation getData() {
            return obs;
        }

        public void setData(WeatherObservation data) {
            this.obs = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
*/