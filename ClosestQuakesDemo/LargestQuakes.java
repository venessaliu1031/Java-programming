import java.util.*;
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LargestQuakes {
    public int indexOfLargest(ArrayList<QuakeEntry> quakeData){
        int largestIndex = -1;
        double largest = 0;
        for (QuakeEntry qe: quakeData) {
            if (qe.getMagnitude() > largest) {
                largest = qe.getMagnitude();
                largestIndex = quakeData.indexOf(qe);
                
            }
        }
        
        return largestIndex;
    }
    
   
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        
        for (int i = 0; i < howMany; i++) {
            int largestIndex = indexOfLargest(copy);
        
            ret.add(copy.get(largestIndex));
            
            copy.remove(largestIndex);
        }
        return ret;
    }
    
   
    public void findLargestQuakes(){
       
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for " + list.size());
        
        //Location jakarta  = new Location(-6.211, 106.845);
        int howMany = 50;
        
        int largestIndex = indexOfLargest(list);

        ArrayList<QuakeEntry> largestQuakes = getLargest(list, howMany);
        for(int k=0; k < largestQuakes.size(); k++){
            
            System.out.println(largestQuakes.get(k));
        }
        System.out.println("number found: " + largestQuakes.size());
    }

}
