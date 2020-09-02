/**
 * Find the highest (hottest) temperature in a file of CSV weather data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord largestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            //If largestSoFar is nothing
            if (largestSoFar == null) {
                largestSoFar = currentRow;
            }
            //Otherwise
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                //Check if currentRow’s temperature > largestSoFar’s
                if (currentTemp > largestTemp) {
                    //If so update largestSoFar to currentRow
                    largestSoFar = currentRow;
                }
            }
            //The largestSoFar is the answer
            
        }
        return largestSoFar;
    }

    public void testHottestInDay() {
            FileResource fr = new FileResource("data/2015/weather-2015-01-01.csv");
            CSVRecord largest = hottestHourInFile(fr.getCSVParser());
            System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                " at " + largest.get("TimeEST"));
    
    }
    
    public void hottestInManyDays() {
        CSVRecord largest = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            if (largest == null) {
                largest = currentRow;
            }
            //Otherwise
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largest.get("TemperatureF"));
                //Check if currentRow’s temperature > largestSoFar’s
                if (currentTemp > largestTemp) {
                    //If so update largestSoFar to currentRow
                    largest = currentRow;
                }
            }
        }
        System.out.println("Highest temperature was " + largest.get("TemperatureF"));
    }
    
    public CSVRecord coldestHourInFile (File f) {
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestRow = null;
        for (CSVRecord currRow: parser) {
            if (lowestRow == null) {
                lowestRow = currRow;
            }
            double temp = Double.parseDouble(currRow.get("TemperatureF"));
            double lowestTemp = Double.parseDouble(lowestRow.get("TemperatureF"));
            if (temp < lowestTemp && temp != -9999) {
                lowestRow = currRow;
            }
            
        }
        return lowestRow;

        
    }
    
    public File fileWithColdestTemp() {
        CSVRecord lowestRow = null;
        DirectoryResource dr = new DirectoryResource();
        File lowestFile = null;
        for (File f: dr.selectedFiles()) {
            
            
            CSVRecord currentRow = coldestHourInFile(f);
            if (lowestRow == null) {
                lowestRow = currentRow;
                lowestFile = f;
            }
            //Otherwise
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(lowestRow.get("TemperatureF"));
                //Check if currentRow’s temperature > largestSoFar’s
                if (currentTemp < lowestTemp) {
                    //If so update largestSoFar to currentRow
                    lowestRow = currentRow;
                    lowestFile = f;
                }
            }
        }
        
        return lowestFile;
        
    }
    
    public void testColdestDay() {
        File lowestDay = fileWithColdestTemp();
        String lowestFileName = lowestDay.getName();
        System.out.println("Coldest day was in file " + lowestFileName);
        CSVRecord coldestHour = coldestHourInFile(lowestDay);
        System.out.println("Coldest temperature on that day was " + coldestHour.get("TemperatureF")+ " F");
        
        
        
    }
    
    public CSVRecord lowestHumidityInFile(File f) {
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestRow = null;
        for (CSVRecord currRow: parser) {
            if (lowestRow == null) {
                lowestRow = currRow;
            }
            
            if (!currRow.get("Humidity").equals("N/A")){
                double hum = Double.parseDouble(currRow.get("Humidity"));
                double lowestHum = Double.parseDouble(lowestRow.get("Humidity"));
                if (hum < lowestHum) {
                    lowestRow = currRow;
                }
            }
            
            
        }
        return lowestRow;
    }
    
    public File fileWithLowestHumidity() {
        CSVRecord lowestRow = null;
        DirectoryResource dr = new DirectoryResource();
        File lowestFile = null;
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            
            CSVRecord currentRow = lowestHumidityInFile(f);
            if (lowestRow == null) {
                lowestRow = currentRow;
                lowestFile = f;
            }
            //Otherwise
            else {
                double currentHum = Double.parseDouble(currentRow.get("Humidity"));
                double lowestHum = Double.parseDouble(lowestRow.get("Humidity"));
                //Check if currentRow’s temperature > largestSoFar’s
                if (currentHum < lowestHum) {
                    //If so update largestSoFar to currentRow
                    lowestRow = currentRow;
                    lowestFile = f;
                }
            }
        }
        
        return lowestFile;
        
    }
    
    public void testLowestHumidity() {
        File lowestDay = fileWithLowestHumidity();
        //String lowestFileName = lowestDay.getName();
        //System.out.println("Day with lowest humidity was in file " + lowestFileName);
        CSVRecord lowestHour = lowestHumidityInFile(lowestDay);
        System.out.println("Lowest humidity on that day was " + lowestHour.get("Humidity")
            + " at " + lowestHour.get("DateUTC"));
        
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        double totalTemp = 0;
        double numOfRecord = 0;
        for (CSVRecord currRow: parser) {
            double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
            if (currTemp != -9999){
                totalTemp = totalTemp + currTemp;
                numOfRecord++;
            }
            
            
            
        }
        
        return (totalTemp / numOfRecord);
    }
    
    public void testAverageTemp() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double aveTemp = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + aveTemp);
        
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double totalTemp = 0;
        double numOfRecord = 0;
        for (CSVRecord currRow: parser) {
            double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
            int currHum = Integer.parseInt(currRow.get("Humidity"));
            if ( currHum >= value && currTemp != -9999){
                totalTemp = totalTemp + currTemp;
                numOfRecord++;
            }
            
            
            
        }
        return (totalTemp/numOfRecord);
    }
    
    public void testAverageTempWithHighHumidity() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double aveTemp = averageTemperatureWithHighHumidityInFile(parser, 80);
        System.out.println("Average Temp when high Humidity is " + aveTemp);
        
    }
}















