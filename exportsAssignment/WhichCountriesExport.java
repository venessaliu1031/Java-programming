/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportOfInterest)) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }

    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();

    }

    public void countryInfo(String country) {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String exports = "";
        String value = "";
        for (CSVRecord record: parser) {
            String currCountry = record.get("Country");
            if (currCountry.equals(country)) {
                exports = record.get("Exports");
                value = record.get("Value (dollars)");
            }

            
        }
        //System.out.println(exports + " " + value);
        if (exports.isEmpty() || value.isEmpty()) {
            System.out.println("NOT FOUND");
        }
        else {
            String output = country + ": " + exports + ": " + value;
            System.out.println(output);
        }

    }


    public void listExportersTwoProducts(String item1, 
    String item2) {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record: parser) {
            String exports = record.get("Exports");
            if (exports.contains(item1) && exports.contains(item2)){
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public void numOfExporter (String item) {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int num = 0;
        for (CSVRecord record: parser) {
            String exports = record.get("Exports");
            if (exports.contains(item)){
                num++;
            }
        }
        System.out.println(num);
    }
    
    public void bigExporter (String amount) {
        String pureAmount = amount.replace("$", "").replace(",", "");
        float floatAmount = Float.parseFloat(pureAmount);
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record: parser) {
            String currValue = record.get("Value (dollars)");
            String pureValue = currValue.replace("$", "").replace(",", "");
            float floatValue = Float.parseFloat(pureValue);
            if (floatValue >= floatAmount){
                System.out.println(record.get("Country") + " " + currValue);
            }
        }
        
    }
}





