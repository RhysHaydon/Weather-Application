package FrontEnd;

import Backend.Database;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rhys
 */
public class GUI {

    private JFrame frame;
    private JPanel mainPanel, bottom;
    private DrawPanel animationPanel;
    private JButton loadButton, displayButton, animationButton, searchButton;
    private JLabel searchLabel;
    private JScrollPane scrollPane;
    private JTable dataTable;
    private JTextField searchField;
    private JEditorPane displayText;
    private int count;
    private String observations;
    Database db;

    public GUI() {

        //Initialise 
        frame = new JFrame();
        mainPanel = new JPanel();
        animationPanel = new DrawPanel();
        bottom = new JPanel();
        scrollPane = new JScrollPane();

        //Initialise Buttons
        loadButton = new JButton("Load");
        loadButton.setPreferredSize(new Dimension(80, 40));
        displayButton = new JButton("Display");
        animationButton = new JButton("Stop Animaiton");
        animationButton.setPreferredSize(new Dimension(150, 40));
        searchButton = new JButton("Search");

        //Initialise Label & Search        
        searchLabel = new JLabel("Search Date");
        searchField = new JTextField("dd/mm/yyyy");
        searchField.setPreferredSize(new Dimension(30, 10));

        // Frame
        frame.setTitle("Weather Observations");
        frame.setSize(1400, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);

        // Main Panel
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setPreferredSize(new Dimension(700, 600));
        frame.getContentPane().add(mainPanel, BorderLayout.WEST);

        // Bottom Panel
        bottom.setAlignmentX(1);
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
        bottom.setMaximumSize(new Dimension(700, 5));

        // Animtion Panel
        animationPanel.setLayout(new BoxLayout(animationPanel, BoxLayout.Y_AXIS));
        animationPanel.setPreferredSize(new Dimension(685, 600));
        frame.getContentPane().add(animationPanel, BorderLayout.EAST);
        AnimationRunner runner = new AnimationRunner(animationPanel, animationButton);
        runner.start();

        //Welcome Screen
        displayText = new JEditorPane();
        displayText.setContentType("text/html");
        displayText.setText("<h2>Welcome to the weather observation viewer!</h2><h3>Press <i>load</i> to load the weather data, then <i>display</i> to view it!</h3>");
        displayText.setEditable(false);
        scrollPane.setViewportView(displayText);

        // Add Stuff to Main Panel
        mainPanel.add(scrollPane);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(bottom);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Add Buttons, Search & Buffer Areas
        bottom.add(Box.createRigidArea(new Dimension(15, 0)));
        bottom.add(loadButton);
        bottom.add(Box.createRigidArea(new Dimension(15, 0)));
        bottom.add(displayButton);
        bottom.add(Box.createRigidArea(new Dimension(15, 0)));
        bottom.add(animationButton);
        bottom.add(Box.createRigidArea(new Dimension(15, 0)));
        bottom.add(searchLabel);
        bottom.add(Box.createRigidArea(new Dimension(15, 0)));
        bottom.add(searchField);
        bottom.add(Box.createRigidArea(new Dimension(15, 0)));
        bottom.add(searchButton);
        bottom.add(Box.createRigidArea(new Dimension(15, 0)));

        //Button Functionality
        loadButton.addActionListener((ActionEvent e) -> {
            if (count == 0) {
                db.loadObservationsFromHTMLFile();
                observations = db.getObservations();
                if (!observations.isEmpty()) {
                    loadButton.setText("Loaded");
                } else {
                    JOptionPane.showMessageDialog(null, "No weather observations could be loaded! Try clicking load again!", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
                count++;
            } else {
                JOptionPane.showMessageDialog(null, "WeatherObservations have already been loaded! Try pressing the display button!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }

        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count == 1) {
                    displayTable();
                    count++;
                } else if (count == 0) {
                    JOptionPane.showMessageDialog(null, "Weather Observations haven't been loaded yet! Try Pressing the load Button!", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Weather Observations have already been loaded!", "Info", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });

        animationButton.addActionListener(new ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (count == 0) {
                    animationButton.setText("Resume Animation");
                    count++;
                } else {
                    animationButton.setText("Stop Animation");
                    count--;
                }
            }
        });

        searchField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                searchField.setText("");
            }
        });

        searchField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent me) {
                if (searchField.getText().equals("")) {
                    searchField.setText("dd/mm/yyyy");
                }
            }
        });

        searchButton.addActionListener((ActionEvent e) -> {
            String format = "dd/mm/yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setLenient(false);
            try {
                Date date = sdf.parse(searchField.getText());
                if (!sdf.format(date).equals(searchField.getText())) {
                    try {
                        searchResults(searchField.getText());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Incorrect date format entered! Try again using dd/mm/yyyy!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Incorrect date format entered! Try again using dd/mm/yyyy!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public void setDB(Database db) {
        this.db = db;
    }

    public void showGUI() {
        frame.setVisible(true);
    }

    private void searchResults(String date) {
        //JOptionPane.showMessageDialog(null, db.checkWeatherByDate(date), "Info", JOptionPane.INFORMATION_MESSAGE);
        System.out.println(db.checkWeatherByDate(date));
    }

    private void displayTable() {
        System.out.println(observations);

        if (observations.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No weather observations have been loaded! Try clicking 'Load' to load from the html file first.", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {

            String[] columns = {"Place", "Date", "Temperature", "Humidity", "UV Index", "Wind Speed"};

            String[] observationsArray = observations.split("#");
            Object[][] data = new Object[observationsArray.length][6];

            for (int i = 0; i < observationsArray.length; i++) {
                String observation = observationsArray[i];
                String[] bits = observation.split(",");
                data[i][0] = bits[0];
                data[i][1] = bits[1];
                data[i][2] = bits[2];
                data[i][3] = bits[3];
                data[i][4] = bits[4];
                data[i][5] = bits[5];
            }

            dataTable = new JTable(data, columns) {
                // disable editing cells (can't change the weather from the past! :P)
                // source: http://www.codeproject.com/Questions/557307/DisableplusEditingplusonplusJTablepluscellplusinpl
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            scrollPane.setViewportView(dataTable);
            dataTable.setFillsViewportHeight(true);
        }
    }
}
