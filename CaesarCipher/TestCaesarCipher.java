import edu.duke.*;
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipher {
    
    
    public void test() {
        CaesarCipher cc = new CaesarCipher(15);
        String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = cc.encrypt(input);
        CaesarCipher cc2 = new CaesarCipher(26-15);
        System.out.println("encrypted message is: " + encrypted);
        System.out.println("decrypted message is: " + cc2.encrypt(encrypted));
        String encrypted2Keys = cc.encryptTwoKeys(input, 21, 8);
        System.out.println("encrypted with two keys: "+ encrypted2Keys);
        //String input2 = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        //System.out.println(cc.encryptTwoKeys(input2, 26-14, 26-24));
        
        CaesarBreaker cb = new CaesarBreaker(1);
        FileResource fr = new FileResource("mysteryTwoKeysQuiz.txt");
        input = fr.asString();
        
        System.out.println(cb.getKey(input));
        
    }

}
