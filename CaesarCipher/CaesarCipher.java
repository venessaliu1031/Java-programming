import edu.duke.*;

public class CaesarCipher {
    
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher (int key) {
        mainKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
        
    }
    
    public String encrypt(String input) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        //Compute the shifted alphabet
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            char currCharUp = Character.toUpperCase(encrypted.charAt(i));
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(currCharUp);
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                if(Character.isUpperCase(currChar)){
                        char newChar = shiftedAlphabet.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                    else {
                        char newChar = Character.toLowerCase(shiftedAlphabet.charAt(idx));
                        encrypted.setCharAt(i, newChar);
                    }
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet1 = alphabet.substring(key1)+
        alphabet.substring(0,key1);
        String shiftedAlphabet2 = alphabet.substring(key2)+
        alphabet.substring(0,key2);
        
        for (int i = 0; i < encrypted.length(); i++){
            char currChar = encrypted.charAt(i);
            char currCharUp = Character.toUpperCase(encrypted.charAt(i));
            int idx = alphabet.indexOf(currCharUp);
            
            if (idx != -1) {
                
                
                if (i % 2 == 0) {
                    if(Character.isUpperCase(currChar)){
                        char newChar = shiftedAlphabet1.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                    else {
                        char newChar = Character.toLowerCase(shiftedAlphabet1.charAt(idx));
                        encrypted.setCharAt(i, newChar);
                    }
                }
                else {
                    if(Character.isUpperCase(currChar)){
                        char newChar = shiftedAlphabet2.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                    else {
                        char newChar = Character.toLowerCase(shiftedAlphabet2.charAt(idx));
                        encrypted.setCharAt(i, newChar);
                    }
                }
            }
            
            
        }
        
        return encrypted.toString();
        
        
        
    }
    
    
    
    
    public void testCaesar() {
        int key = 15;
        //FileResource fr = new FileResource();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String encrypted = encrypt(message);
        System.out.println(encrypted);
        //String decrypted = encrypt(encrypted, 26-key);
        //System.out.println(decrypted);
    }
    
    public void testTwoKey() {
        String output = encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21);
        System.out.println(output);
        
    }
}

