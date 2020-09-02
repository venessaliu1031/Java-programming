
import edu.duke.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map;
    
    public WordsInFiles() {
        map = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        String fileName = f.getName();
        
        for (String word: fr.words()) {
            if (map.containsKey(word)) {
                ArrayList al = map.get(word);
                if(!al.contains(fileName)){
                    al.add(fileName);
                }
                map.replace(word, al);
                
            } else {
                ArrayList al = new ArrayList<String>();
                al.add(fileName);
                map.put(word, al);
                
            }
            
        }
        
    }
    
    public void buildWordFileMap() {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
            // process each file in turn
        }
        
    }
    
    public int maxNumber() {
        int fileNumber = 0;
        String maxWord = "";
        for (String word: map.keySet()){
            int num = map.get(word).size();
            if (num > fileNumber){
                fileNumber = num;
                maxWord = word;
            }
        }
        return fileNumber;
    }
    
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList words = new ArrayList<String>();
        for (String word: map.keySet()) {
            if (map.get(word).size() == number){
                words.add(word);
            }
        }
        return words;
    }
    
    public void printFilesIn(String word) {
        ArrayList fileNames = new ArrayList<String>();
        fileNames = map.get(word);
        for (int i = 0; i < fileNames.size(); i++) {
            System.out.println(fileNames.get(i));
        }
    }
    
    public void tester() {
        buildWordFileMap();
        int maxFileNum = maxNumber();
        ArrayList<String> maxNumWords = wordsInNumFiles(4);
        
        System.out.println("The greatest number of files a word appears in is " + maxFileNum + " and the words are:");
        //for (int i = 0; i < maxNumWords.size(); i++) {
        //    System.out.println(maxNumWords.get(i) + " appears in files:");
        //    printFilesIn(maxNumWords.get(i));
            
        //}
        
        System.out.println(maxNumWords.size() + " words appear in four files");
        System.out.println("The word tree appears in files: " + map.get("tree"));
        
        
        
    }
    
}








