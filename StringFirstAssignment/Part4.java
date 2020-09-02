import edu.duke.*;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    public void getYoutubeLinks() {
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        int startIndex = 0;
        int endIndex = 0;
        int pos = 0;
        for (String word: ur.words()) {
            String wordlower = word.toLowerCase();
            pos = wordlower.indexOf("youtube.com");
            if (pos != -1) {
                startIndex = wordlower.lastIndexOf("\"", pos);
                endIndex = wordlower.indexOf("\"", pos);
                System.out.println(word.substring(startIndex+1, endIndex));
            }
            
        }
        
    }

}
