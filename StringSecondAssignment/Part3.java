
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            if ((currIndex-startIndex) % 3 == 0) {
                return currIndex;
            }
            else {
                currIndex = dna.indexOf(stopCodon, currIndex+1);
            }
        
        }
        return dna.length();
    }
    
    public int findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return -1;
        }
        int taaIndex = findStopCodon(dna, startIndex+3, "TAA");
        int tagIndex = findStopCodon(dna, startIndex+3, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex+3, "TGA");
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        return minIndex;
        
        
    }
    
    public int countGene(String dna) {
        int count = 0;
        while(true) {
            int currIndex = findGene(dna);
            if (currIndex == -1) {
                break;
            }
            else {
                count++;
                dna = dna.substring(currIndex+3);
            }
        }
        return count;
    }
    
    public void testStopCodon() {
                    //V  V  V  V  
        String dna = "ATGAATAAATAA";
        System.out.println("Stop Codon Index is: " + findStopCodon(dna,0,"TAG"));
        
        
    }
    
    public void testFindGene() {
        
        String dna1 = "xxxATGxxxyTAGzzTAGxxxTAG";
        System.out.println("DNA strand 1: " + dna1);
        System.out.println("Gene found: " + findGene(dna1));
        
        String dna2 = "xxxxxxyTAGzzTAGxxxTAG";
        System.out.println("DNA strand 2: " + dna2);
        System.out.println("Gene found: " + findGene(dna2));
        
        String dna3 = "xxxATGzzzxxxTAG";
        System.out.println("DNA strand 3: " + dna3);
        System.out.println("Gene found: " + findGene(dna3));
    }
    
    public void testCountGene() {
        String dna1 = "ATGATGAAATAA";
        System.out.println("DNA strand: " + dna1);
        System.out.println("Has " + countGene(dna1) + " genes in it.");
    }

}
