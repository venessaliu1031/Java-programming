
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
        int totaloccur = -1;
        int index = 0;
        while (index > -1) {
            index = stringb.indexOf(stringa, index+1);
            System.out.println(index);
            totaloccur = totaloccur + 1;
        }
        if (totaloccur > 2) {
            return true;
        } else {
            return false;
        }
    }
    
    public void testTwoOccurrences() {
        String a = "ab";
        String b = "babababab";
        System.out.println("String a: " + a);
        System.out.println("String b: " + b);
        boolean result = twoOccurrences(a,b);
        System.out.println("if more than 2 occurrences: " + result);
    }

}
