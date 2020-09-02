import edu.duke.*;

/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts){
        
        for (String s : resource.words()){
            int length = s.length();
            
            
            if (length > 0 && !Character.isLetter(s.charAt(0))){
                length -= 1;
            } 
            if (length > 0 && !Character.isLetter(s.charAt(length-1))){
                length -= 1;
            }
            
            if (length >= counts.length){
                counts[counts.length - 1] += 1;
            } else {
                counts[length] += 1;
            }
            
            
            
        }
        
        
    }
    
    public void testCountWordLengths() {
        int[] counts = new int[31];
        FileResource resource = new FileResource("manywords.txt");
        
        countWordLengths(resource, counts);
        System.out.println("Word Length Counts");
        
        for (int k = 0; k < counts.length; k++){
            System.out.println(k + " length word count: " + counts[k]);
        }
        
        
    }
    

}
