
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    
    public int howMany(String stringa, String stringb) {
        int currIndex = stringb.indexOf(stringa);
        int totalrepeat = 0;
        while (true) {
            if (currIndex == -1) {
                break;
            }
            currIndex = stringb.indexOf(stringa, currIndex+stringa.length());
            totalrepeat++;
        }
        return totalrepeat;
        
    }
    
    public void testHowMany() {
        String a = "AA";
        String b = "ATAAAA";
        System.out.println(a + " occurs in " + b + " " + howMany(a,b) + 
        " times.");
        
        String a1 = "GAA";
        String b1 = "ATGAACGAATTGAATC";
        System.out.println(a1 + " occurs in " + b1 + " " + howMany(a1,b1) + 
        " times.");
    }

}
