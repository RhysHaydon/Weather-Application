package Backend;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WeatherObservation implements Serializable {

    private String place = "";
    private Date date = new Date();
    private Double temperature = 0.0;
    private Double humidity = 0.0;
    private Double uvIndex = 0.0;
    private Double windSpeed = 0.0;
    private static Logger logger = Logger.getLogger(WeatherObservation.class.getName());

    public WeatherObservation() {

    }

    public WeatherObservation(String place, String date, Double temperature, Double humidity, Double uvIndex, Double windSpeed) {
        this.place = place;
        setDateFromString(date);
        this.temperature = temperature;
        this.humidity = humidity;
        this.uvIndex = uvIndex;
        this.windSpeed = windSpeed;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateAsString() {
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        return format.format(this.date);
    }

    public void setDateFromString(String date) {
        try {
            this.date = new SimpleDateFormat("dd/mm/yyyy").parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(WeatherObservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Binary Print Method
    /*
    String addData;
    public String print(Node p) {
        if (p != null) {
            print(p.left);
            // concatenate allData and p.data 
            print(p.right);
        }
        return allData;
    }
    */

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(Double uvIndex) {
        this.uvIndex = uvIndex;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Override
    public String toString() {
        //DecimalFormat dFormat = new DecimalFormat("##0.0");
        String dFormat = "%4.1f";
        String output = place + " on " + date + ": "
                + String.format(dFormat, temperature) + "°C, "
                + String.format(dFormat, humidity) + " RH, "
                + String.format(dFormat, uvIndex) + " UV, "
                + String.format(dFormat, windSpeed) + "km/h wind speed";

        return output;
    }

    public String formattedString() {
        return String.format("%s,%s,%s,%s,%s,%s", place, getDateAsString(), temperature, humidity, uvIndex, windSpeed);
    }
}
