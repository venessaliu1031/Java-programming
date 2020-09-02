import java.util.ArrayList;
import edu.duke.*;
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        
        FileResource fr = new FileResource();
        
        for (String s : fr.words()) {
            s = s.toLowerCase();
            int idx = myWords.indexOf(s);
            if (idx == -1) {
                myWords.add(s);
                myFreqs.add(1);
            } else {
                int freq = myFreqs.get(idx);
                myFreqs.set(idx,freq+1);
            }
        }
        
    }
    
    public void tester(){
        findUnique();
        System.out.println("# unique words: "+myWords.size());
        int index = findMax();
        //for (int i = 0; i < myWords.size(); i++){
        //    System.out.println(myWords.get(i) + "\t" + myFreqs.get(i));
        //}
        System.out.println("max word/freq: "+myWords.get(index)+" "+myFreqs.get(index));
    }
    
    public int findMax(){
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for(int k=0; k < myFreqs.size(); k++){
            if (myFreqs.get(k) > max){
                max = myFreqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }

}
