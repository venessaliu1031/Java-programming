import edu.duke.*;
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarBreaker {
    
    private int mainKey;
    private CaesarCipher cc;
    
    public CaesarBreaker(int key) {
        mainKey = key;
        cc = new CaesarCipher(mainKey);
    }
    
    private int[] countLetters(String encrypted) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < encrypted.length(); k++) {
            char ch = Character.toLowerCase(encrypted.charAt(k));
            int idx = alpha.indexOf(ch);
            if (idx != -1) {
                counts[idx] += 1;
            }
        }
        for (int k = 0; k < counts.length; k++){
            System.out.println(k + " index letter count: " + counts[k]);
        }
        return counts;
    }
    
    
    public int maxIndex(int[] counts) {
        int maxFreq = 0;
        int maxDex = -1;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > maxFreq) {
                maxFreq = counts[i];
                maxDex = i;
            }
        }
        System.out.println(maxDex);
        return maxDex;
    }
    
    public String halfOfString(String s, int half) {
        StringBuilder halfString = new StringBuilder();
        for (int i = half; i < s.length(); i += 2){
            halfString.append(s.charAt(i));
        }
        System.out.println("this half string is: " + halfString);
        return halfString.toString();
        
    }
    
    public int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxFreq = maxIndex(freqs);
        int dkey = maxFreq - 4;
        if (maxFreq < 4) {
            dkey = 26 - 4 + maxFreq;
        }
        return dkey;
    }
    
    public String decrypt(String encrypted) {
        
        
        
        
        
        String firstHalf = halfOfString(encrypted, 0);
        String secondHalf = halfOfString(encrypted, 1);
        //String message = cc.encrypt(encrypted, 26 - key);
        int key1 = getKey(firstHalf);
        int key2 = getKey(secondHalf);
        
        
        System.out.println("two keys are: " + key1 + " and " + key2);
        return cc.encryptTwoKeys(encrypted, 26-key1, 26-key2);
        //return message;
        
        
    }
    
    public void testDecrypt() {
        FileResource fr = new FileResource("mysteryTwoKeysPractice.txt");
        String encrypted = fr.asString();
        //String s = "Top ncmy qkff vi vguv vbg ycpx";
        System.out.println(decrypt(encrypted));
    }

}
