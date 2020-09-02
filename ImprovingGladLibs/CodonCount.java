import java.util.*;
import edu.duke.*;
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CodonCount {
    private HashMap<String, Integer> codonCount;
    
    public CodonCount() {
        codonCount = new HashMap<String, Integer>();
        
    }
    
    public void buildCodonMap(int start, String dna) {
        codonCount.clear();
        for (int i = start; i < dna.length()-3; i += 3) {
            String codon = dna.substring(i, i+3);
            if (codonCount.containsKey(codon)) {
                codonCount.put(codon, codonCount.get(codon)+1);
            } else {
                codonCount.put(codon, 1);
            }
        }
        
    }
    
    public String getMostCommonCodon() {
        int maxCount = 0;
        String maxCodon = "Not found";
        for (String s: codonCount.keySet()) {
            if (codonCount.get(s) > maxCount) {
                maxCount = codonCount.get(s);
                maxCodon = s;
            }
            
        }
        
        return maxCodon;
    }
    
    public void printCodonCounts(int start, int end) {
        
        for (String s: codonCount.keySet()) {
            int count = codonCount.get(s);
            if (count >= start && count <= end) {
                System.out.println(s + "\t" + count);
            }
        }
        
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String s = fr.asString().toUpperCase().trim();
        for (int i = 0; i < 3; i++){
            buildCodonMap(i, s);
            System.out.println("Open reading frame " + i + ":");
            System.out.println("Number of unique codon: " + codonCount.size());
            String mostCommonCodon = getMostCommonCodon();
            System.out.println("Most common codon is: " + mostCommonCodon + " which appears " + codonCount.get(mostCommonCodon) + " times in this open reading frame.");
            
            printCodonCounts(7,7);
        }
        
        
        
    }

}
