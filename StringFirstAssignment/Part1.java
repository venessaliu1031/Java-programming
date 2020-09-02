
import edu.duke.*;
import java.io.File;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    
    public String findSimpleGene(String dna) {
        int startCodon = dna.indexOf("ATG");
        if (startCodon == -1) {
            return "";
        }
        int endCodon = dna.indexOf("TAA");
        if (endCodon == -1) {
            return "";
            
        }
        if ((endCodon - startCodon) % 3 == 0) {
            String result = dna.substring(startCodon, endCodon+3);
            return result;
        }
        return "";
    }
    
    public void testSimpleGene() {
        String dna = "atgtaa";
        String result = findSimpleGene(dna);
        System.out.println("DNA strand is " + dna);
        System.out.println("Gene is " + result);
    }

}
