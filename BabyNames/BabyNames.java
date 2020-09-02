import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of BabyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BabyNames {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                    " Gender " + rec.get(1) +
                    " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalGirlNames = 0;
        int totalBoyNames = 0;
        int totalNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                totalBoyNames += 1;

            }
            else {
                totalGirls += numBorn;
                totalGirlNames += 1;
            }
            totalNames += 1;
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
        System.out.println("total boy names = " + totalBoyNames);
        System.out.println("total girl names = " + totalGirlNames);
        System.out.println("total names = " + totalNames);
    }

    public void testTotalBirths (int year) {
        //FileResource fr = new FileResource();
        String fileName = "data/yob"+ year+ ".csv";
        FileResource fr = new FileResource(fileName);
        totalBirths(fr);
    }

    public void printAllData(int year){
        String fileName = "data/yob"+ year+ ".csv";
        FileResource fr = new FileResource(fileName);
        for (CSVRecord rec : fr.getCSVParser(false)) {
            System.out.println(rec.get(0) + " " + rec.get(1) + " "+ rec.get(2));
        }

    }

    public int getRank(int year, String name, String gender) {
        String fileName = "data/yob"+ year+ ".csv";
        FileResource fr = new FileResource(fileName);
        int rank = 0;
        int ifFound = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String currName = rec.get(0);
            String currGender = rec.get(1);
            if (currGender.equals(gender)){
                rank++;
                if (currName.equals(name)) {
                    ifFound = 1;
                    break;
                }
            }
        }

        if (ifFound == 1) { 
            return rank;
        }
        else {
            return -1;
        }

    }

    public void testGetRank() {
        int year = 1971;
        String name = "Frank";
        String gender = "M";
        int rank = getRank(year, name, gender);
        System.out.println("Rank of "+ name + " in the year of " + year + " is " + rank);
    }

    public String getName(int year, int rank, String gender) {
        String fileName = "data/yob"+ year+ ".csv";
        FileResource fr = new FileResource(fileName);
        int currRank = 0;
        String name = "";
        int isFound = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String currName = rec.get(0);
            String currGender = rec.get(1);
            if (currGender.equals(gender)) {
                currRank++;
                if (currRank == rank) {
                    name = rec.get(0);
                    isFound = 1;
                    break;
                }
            }
        }

        if (isFound == 1) {
            return name;
        }
        else{
            return "Name not found";
        }
    }

    public void testGetName() {
        int year = 1982;
        int rank = 450;
        String gender = "M";
        String name = getName(year, rank, gender);
        System.out.println("Top "+ rank + " girls name in the year of " + year 
            + " is " + name);
    }

    public void whatIsNameInYear(String name, int year, int newyear, String gender){
        String outputGender = "she";
        int rank = getRank(year, name, gender);
        String parallelName = getName(newyear, rank, gender);
        if (gender.equals("M")) {
            outputGender = "he";
        }
        System.out.println(name + " born in " + year + " would be " + parallelName 
            + " if " + outputGender + " was born in "+ newyear); 
    }

    public String yearOfHighestRank(String name, String gender) {
        int highestRank = -1;
        DirectoryResource dr = new DirectoryResource();
        String highestYear = "";
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String fileName = f.getName();
            int currRank = 0;
            int isFound = 0;

            for (CSVRecord rec : fr.getCSVParser(false)) {
                String currName = rec.get(0);
                String currGender = rec.get(1);
                if (currGender.equals(gender)){
                    currRank++;
                    if (currName.equals(name)) {
                        isFound = 1;
                        break;
                    }
                }

            }
            
            if (isFound == 1) {
                if (highestRank == -1){
                    highestYear = fileName.substring(3,7);
                    highestRank = currRank;
                }
                else {
                    if (currRank < highestRank) {
                        highestRank = currRank;
                        highestYear = fileName.substring(3,7);
                    }
                }
                
                
            }
            
        }

        return highestYear;
    }
    
    public double getAverageName(String name, String gender) {
        double totalRank = 0;
        double numOfYear = 0;
        DirectoryResource dr = new DirectoryResource();
        String highestYear = "";
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String fileName = f.getName();
            int currRank = 0;
            int isFound = 1;
            numOfYear++;

            for (CSVRecord rec : fr.getCSVParser(false)) {
                String currName = rec.get(0);
                String currGender = rec.get(1);
                if (currGender.equals(gender)){
                    currRank++;
                    if (currName.equals(name)) {
                        isFound = 1;
                        break;
                    }
                }

            }
            
            if (isFound == 1) {
                totalRank += currRank;
            }
            
        }
        
        

        return totalRank/numOfYear;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int totalBorn = 0;
        String fileName = "data/yob"+ year+ ".csv";
        FileResource fr = new FileResource(fileName);
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int currBorn = Integer.parseInt(rec.get(2));
            String currName = rec.get(0);
            String currGender = rec.get(1);
            if (currGender.equals(gender)) {
                if (currName.equals(name)) {
                    break;
                }
                else{
                    totalBorn += currBorn;
                }
            }
            
        }
        
        
        return totalBorn;
    }
    

}





