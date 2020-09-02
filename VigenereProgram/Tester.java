import java.util.*;
import edu.duke.*;
import java.io.*;
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tester {
    
    
    public void testCaesarCipher() {
        CaesarCipher cc = new CaesarCipher(5);
        FileResource fr = new FileResource();
        String input = fr.asString();
        System.out.println("input: " + input);
        String encrypted = cc.encrypt(input);
        System.out.println("encrypted: " + encrypted);
        System.out.println("decrypted: " + cc.decrypt(encrypted));
        
    }
    
    public void testCaesarCracker() {
        CaesarCracker ccr = new CaesarCracker();
        FileResource fr = new FileResource();
        String input = fr.asString();
        System.out.println("input: " + input);
        System.out.println("decrypted: " + ccr.decrypt(input));
        
    }
    
    public void testVigenereCipher() {
        int[] keys = {17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(keys);
        FileResource fr = new FileResource();
        String input = fr.asString();
        System.out.println("input: " + input);
        String encrypted = vc.encrypt(input);
        System.out.println("encrypted: " + vc.encrypt(input));
        System.out.println("decrypted: " + vc.decrypt(encrypted));
        System.out.println("key string: " + vc.toString());
    }
    
    public void testVigenereBreaker() {
        VigenereBreaker vb = new VigenereBreaker();
        //System.out.println(vb.sliceString("abcdefghijklm", 4, 5));
        //int[] keys = vb.tryKeyLength(4, 'e');
        
        System.out.println("decrypted message: ");
        //System.out.println(vb.breakVigenere(38, 'e'));
        System.out.println("max word count is " + vb.maxWordCount);
        int klength = vb.klength;
        System.out.println("key length is " + klength);
        System.out.println("keys are: ");
        int[] key = vb.tryKeyLength(klength, 'e');
        for (int i = 0; i< klength; i++) {
            System.out.print(key[i] + " ");
        }
        //for (int i = 0; i< keys.length; i++) {
        //    System.out.println("key for " + i + "th slice is: " + keys[i]);
        //}
    }
    
    public void testVigenereBreakerForUnknownLanguage() {
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println("decrypted file:");
        System.out.println(vb.breakVigenere());
        HashMap<String, HashSet<String>> languages = vb.languages;
        System.out.println("language: " + vb.breakForAllLangs(languages));
        System.out.println("max word count is " + vb.maxWordCount);
    }
    
    

}







