package timeconverterapp;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class TimeConverterApp {
   private final Map<String, String> countryToTimeZone = new HashMap<>();
   private final JComboBox<String> sourcecountryComboBox;
   private final JComboBox<String> targetcountryComboBox;
   private final JLabel resultLabel;
   private final JLabel targetresultLabel;
   

    
    public TimeConverterApp(){
    
    
        countryToTimeZone.put("United Kingdom", "Europe/London");
        countryToTimeZone.put("Japan", "Asia/Tokyo");
        countryToTimeZone.put("India", "Asia/Kolkata");//
        countryToTimeZone.put("Australia", "Australia/Sydney");
        countryToTimeZone.put("Germany", "Europe/Berlin");
        
        JFrame frame = new JFrame("Time Zone Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLocation(350,100);
        frame.setVisible(true);
        
        
        sourcecountryComboBox = new JComboBox<>(countryToTimeZone.keySet().toArray(new String[0]));
        targetcountryComboBox = new JComboBox<>(countryToTimeZone.keySet().toArray(new String[0]));
        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel();
        targetresultLabel = new JLabel();
        
        
        JPanel panel = new JPanel(new GridLayout(5, 1));
        panel.add(new JLabel("Select a Source Country:"));
        panel.add(sourcecountryComboBox);
        panel.add(resultLabel);
        panel.add(new JLabel(" "));
        
        panel.add(new JLabel("Select a Target Country:"));
        panel.add(targetcountryComboBox);
        panel.add(convertButton);
        panel.add(new JLabel(" "));
        panel.add(targetresultLabel);
        

        frame.add(panel);
        
        
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm z");
                 
                String sourceCountry = (String)sourcecountryComboBox.getSelectedItem();
                String sourcetimeZoneId = countryToTimeZone.get(sourceCountry);
                
                ZoneId zoneId = ZoneId.of(sourcetimeZoneId);
                ZonedDateTime localDateTime =  ZonedDateTime.now(zoneId);
                
                String sourceTimeFormatted = localDateTime.format(formatter);
                
                resultLabel.setText("Current Time in " + sourceCountry + ": " + sourceTimeFormatted);
                
                
                String targetCountry = (String)targetcountryComboBox.getSelectedItem();
                String targettimeZoneId = countryToTimeZone.get(targetCountry);
  
                ZoneId tzoneId = ZoneId.of(targettimeZoneId);
                ZonedDateTime tlocalDateTime =  ZonedDateTime.now(tzoneId);
                
                String targetTimeFormatted = tlocalDateTime.format(formatter);  
                
                targetresultLabel.setText("Current Time in " + targetCountry + ": " + targetTimeFormatted);
                
            }
        });
        
    }
        
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TimeConverterApp();
            }
        });
    }
}
   
       
    
       
       
