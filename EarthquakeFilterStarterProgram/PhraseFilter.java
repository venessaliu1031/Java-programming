
/**
 * Write a description of Phrase here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter {
    private String where;
    private String phrase;

    public PhraseFilter(String w, String p) {
        where = w;
        phrase = p;
    }

    public boolean satisfies(QuakeEntry qe) {
        if (where.equals("start")){
            if (qe.getInfo().substring(0, phrase.length()).equals(phrase)){
                return true;
            }
        }

        if (where.equals("end")){
            String title = qe.getInfo();
            if (title.substring(title.length()-phrase.length()).equals(phrase)){
                return true;
            }
        }

        if (where.equals("any")){
            String title = qe.getInfo();
            if (title.contains(phrase)){
                return true;
            }
        }
        return false;
    }
    
    public String getName() {
        return "Phrase";
    }

}
