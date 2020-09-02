import java.util.*;
import edu.duke.*;
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlay {
    private ArrayList<String> characters;
    private ArrayList<Integer> freqs;
    
    public CharactersInPlay() {
        characters = new ArrayList<String>();
        freqs = new ArrayList<Integer>();
    }
    
    public void update(String name) {
        int idx = characters.indexOf(name);
        if (idx == -1){
            characters.add(name);
            freqs.add(1);
        } else {
            int freq = freqs.get(idx);
            freqs.set(idx, freq+1);
        }
    }
    
    public void findAllCharacters() {
        characters.clear();
        freqs.clear();
        FileResource fr = new FileResource();
        for(String line : fr.lines()){
            int pindex = line.indexOf(".");
            if (pindex != -1) {
                String name = line.substring(0,pindex);
                name = name.toUpperCase();
                update(name);
                
            }
        }
        
    }
    
    public void tester() {
        findAllCharacters();
        for (int i = 0; i < characters.size(); i++) {
            if (freqs.get(i) > 10) {
                System.out.println(characters.get(i) + "\t" + freqs.get(i));
            }
            
        }
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        findAllCharacters();
        for (int i = 0; i < characters.size(); i++) {
            int freq = freqs.get(i);
            if (freq >= num1 && freq <= num2) {
                System.out.println(characters.get(i) + "\t" + freqs.get(i));
            }
            
        }
    }

}
