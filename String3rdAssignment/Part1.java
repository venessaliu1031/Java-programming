import edu.duke.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex);
        while (currIndex != -1) {
            if ((currIndex-startIndex) % 3 == 0) {
                return currIndex;
            }
            else {
                currIndex = dna.indexOf(stopCodon, currIndex+1);
            }
        
        }
        return -1;
    }
    
    public String findGene(String dna, int where) {
        System.out.println(where);
        if (where >= dna.length()) {
            return "";
        }
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex+3, "TAA");
        
        int tagIndex = findStopCodon(dna, startIndex+3, "TAG");
        
        int tgaIndex = findStopCodon(dna, startIndex+3, "TGA");
        
        int minIndex = 0;
        if (taaIndex == -1 ||
            (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        }
        else {
            minIndex = taaIndex;
        }
        if (minIndex == -1 ||
            (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }
        System.out.println(minIndex);
        if (minIndex == -1) {
            return "no stop codon";
        }
        
        else {
            
            return dna.substring(startIndex, minIndex+3);
        }
        
    }
    
    public void testStopCodon() {
                    //V  V  V  V  
        String dna = "ATGAATAAATAA";
        System.out.println("Stop Codon Index is: " + findStopCodon(dna,0,"TAG"));
        
        
    }
    
    public void testFindGene() {
        
        String dna1 = "xxxATGxxxyTAGzzTAGxxxTAG";
        System.out.println("DNA strand 1: " + dna1);
        System.out.println("Gene found: " + findGene(dna1, 0));
        
        String dna2 = "xxxxxxyTAGzzTAGxxxTAG";
        System.out.println("DNA strand 2: " + dna2);
        System.out.println("Gene found: " + findGene(dna2, 0));
        
        String dna3 = "xxxATGzzzxxxTAG";
        System.out.println("DNA strand 3: " + dna3);
        System.out.println("Gene found: " + findGene(dna3, 0));
    }
    
    public StorageResource getAllGenes(String dna) {
        StorageResource sr = new StorageResource();
        String gene = findGene(dna, 0);
        int newIndex = 0;
        while(true) {
            
            if (gene.isEmpty()) {
                break;
            }
            
            else {
                sr.add(gene);
                
                newIndex = dna.indexOf(gene, newIndex)+gene.length();
                gene = findGene(dna, newIndex);
            }
        }
        return sr;
    }
    
    public void testGetAllGenes() {
        String dna = "ATGAACCCTAA";
        StorageResource sr = getAllGenes(dna);
        int geneNum = 0;
        for (String item : sr.data()) {
            geneNum++;
        }
        System.out.println(geneNum);
        
    }
    
    public float cgRatio(String dna) {
        int cgNum = 0;
        for (int i = 0; i< dna.length(); i++) {
            if (dna.charAt(i) == 'C' || dna.charAt(i) == 'G') {
                cgNum++;
            }
        }
        float result = (float)cgNum/dna.length();
        return result;
    }
    
    public void testCGRatio() {
        String dna = "CCCCCCCCC";
        float cgratio = cgRatio(dna);
        System.out.println(cgratio);
    }
    
    public void processGene(StorageResource sr) {
        
        int longestLength = 0;
        int geneNum = 0;
        int geneOver60 = 0;
        int cgHighGene = 0;
        for (String item : sr.data()) {
            if (item.length() > longestLength) {
                longestLength = item.length();
            }
            if (item.length() > 60) {
                geneOver60++;
            }
            if (cgRatio(item) > 0.35) {
                cgHighGene = cgHighGene + 1;
            }
            geneNum = geneNum + 1;
            //}
            
        }
        System.out.println("gene number: " + geneNum + " ");
        System.out.println("Longest gene length: " + longestLength);
        System.out.println("number of gene larger than 60: " + geneOver60);
        
    }
    
    public void testProcessGene() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        dna = dna.toUpperCase();
        StorageResource sr = getAllGenes(dna);
        processGene(sr);
        int index = dna.indexOf("CTG", 0);
        int ctgCount = 0;
        while (true) {
            
            if (index == -1) {
                break;
            }
            else {
                ctgCount++;
                index = dna.indexOf("CTG", index+1);
            }
        
        }
        System.out.println("CTG Count: " + ctgCount);
    
   

    }
}
