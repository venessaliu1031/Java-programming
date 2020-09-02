
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, int startCodon, int stopCodon) {
        
        if (dna.substring(startCodon, startCodon+3) == "ATG") {
            return dna.substring(startCodon, stopCodon+3);
        } else if(dna.substring(startCodon, startCodon+3) == "atg") {
            return dna.substring(startCodon, stopCodon+3);
        }
        
        
        return "";
    }
    
    public void testSimpleGene() {
        String dna = "ATGGGGTAA";
        String result = findSimpleGene(dna,0,6);
        System.out.println("DNA strand is " + dna);
        System.out.println("Gene is " + result);
    }

}
