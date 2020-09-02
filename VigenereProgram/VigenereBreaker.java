import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    
    private String encrypted;
    public int klength;
    public int[] key;
    public int maxWordCount;
    public HashMap<String, HashSet<String>> languages;
    
    public VigenereBreaker() {
        //1. Create a new FileResource using its default constructor (which displays a dialog for you to select a file to decrypt).
        FileResource fr = new FileResource();
        encrypted = fr.asString();
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dict = new HashSet<String>();
        for (String line: fr.lines()) {
            line = line.toLowerCase();
            dict.add(line);
        }
        return dict;
    }
    
    public int countWords(String message, HashSet<String> dict) {
        int count = 0;
        
        for (String word: message.split("\\W+")){
            word = word.toLowerCase();
            if (dict.contains(word)) {
                count++;
            }
        }
        return count;
    }
    
    public char mostCommonCharIn(HashSet<String> dict) {
        char mostCommonChar = '.';
        int maxCount = 0;
        HashMap<Character, Integer> letterCounts = new HashMap<Character, Integer>();
        for (String word: dict) {
            for (int i = 0; i < word.length(); i++){
                char letter = word.charAt(i);
                if (letterCounts.containsKey(letter)) {
                    letterCounts.put(letter, letterCounts.get(letter)+1);
                    
                } else {
                    letterCounts.put(letter, 1);
                }
            }
        }
        for (char l: letterCounts.keySet()) {
            int count = letterCounts.get(l);
            if (count > maxCount) {
                maxCount = count;
                mostCommonChar = l;
            }
        }
        
        return mostCommonChar;
    }
    
    public String sliceString(int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder slice = new StringBuilder();
        for (int i = whichSlice; i < encrypted.length(); i += totalSlices){
            slice.append(encrypted.charAt(i));
        }
        return slice.toString();
    }

    public int[] tryKeyLength(int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        for (int i = 0; i< klength; i++){
            String slice = sliceString(i, klength);
            CaesarCracker ccr = new CaesarCracker();
            key[i] = ccr.getKey(slice);
            
        }
        
        return key;
    }

    public String breakVigenere () {
        //WRITE YOUR CODE HERE
        //FileResource fr = new FileResource();
        //HashSet<String> dict = readDictionary(fr);
        languages = new HashMap<String, HashSet<String>>();
        
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            HashSet<String> dict = readDictionary(fr);
            languages.put(f.getName(), dict);
            System.out.println("dictionary created for " + f.getName());
        }
        String bestLanguage = breakForAllLangs(languages);
        char mostCommon = mostCommonCharIn(languages.get(bestLanguage));
        //3. Use the tryKeyLength method, which you just wrote, to find the key for the message you read in. For now, you should just pass ‘e’ for mostCommon.
        int[] keys = tryKeyLength(klength, mostCommon);
        VigenereCipher vc = new VigenereCipher(keys);
        String decrypted = vc.decrypt(encrypted);
        maxWordCount = countWords(decrypted, languages.get(bestLanguage));
        
        
        return decrypted.substring(0, 200);
        //4. You should create a new VigenereCipher, passing in the key that tryKeyLength found for you.
        //5. You should use the VigenereCipher’s decrypt method to decrypt the encrypted message.
        //6. Finally, you should print out the decrypted message!
    }
    
    public int breakForLanguage(char mostCommon, HashSet<String> dict) {
        //FileResource fr = new FileResource();
        //readDictionary(fr);
        maxWordCount = 0;
        klength = 1;
        for (int i = 1; i < 100; i++){
            int[] key = tryKeyLength(i, mostCommon);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int wordCount = countWords(decrypted, dict);
            if (wordCount > maxWordCount) {
                maxWordCount = wordCount;
                klength = i;
            }
            
            
        }
        
        //String decrypted = breakVigenere();
        return maxWordCount;
        
    }
    
    public String breakForAllLangs(HashMap<String, HashSet<String>> languages) {
        String bestLang = "";
        int maxWordCount = 0;
        for (String lan: languages.keySet()) {
            HashSet<String> dict = languages.get(lan);
            char mostCommonLetter = mostCommonCharIn(dict);
            int wordCount = breakForLanguage(mostCommonLetter, dict);
            if (wordCount > maxWordCount) {
                maxWordCount = wordCount;
                bestLang = lan;
            }
        }
        
        return bestLang;
        
    }
    
}
