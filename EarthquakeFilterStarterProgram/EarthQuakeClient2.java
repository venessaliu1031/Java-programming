import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> filtered = new ArrayList<QuakeEntry>();
        Filter f1 = new MagnitudeFilter(4.0, 5.0);
        Filter f2 = new DepthFilter(-35000.0, -12000.0);
        
        
        
        Location Japan = new Location(35.42, 139.43);
        
        // Filter f1 = new DistanceFilter(Japan, 10000000.0); 
        // Filter f2 = new PhraseFilter("end", "Japan");
        
        for (QuakeEntry qe: list){
            if (f1.satisfies(qe) && f2.satisfies(qe)){
                filtered.add(qe);
            }
            
        }
        
        
        
        for (QuakeEntry qe: filtered) { 
            System.out.println(qe);
        } 
        
        System.out.println("found " + filtered.size() + " quakes that match the criteria");
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }
    
    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        
        ArrayList<QuakeEntry> filtered = new ArrayList<QuakeEntry>();
        
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new DepthFilter(-180000.0, -30000.0));
        maf.addFilter(new PhraseFilter("any", "o"));
        maf.addFilter(new MagnitudeFilter(1.0, 4.0));
        
        for (QuakeEntry qe: list) {
            
            if (maf.satisfies(qe)) {
                filtered.add(qe);
                System.out.println("quake found.");
            }
        }
        
        for (QuakeEntry qe: filtered) { 
            System.out.println(qe);
        }
        
        System.out.println("found " + filtered.size() + " quakes that match the criteria");
        System.out.println("and they are: " + maf.getName());
        
    }
    
    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        
        ArrayList<QuakeEntry> filtered = new ArrayList<QuakeEntry>();
        
        MatchAllFilter maf = new MatchAllFilter();
        
        // Location Tulsa = new Location(36.1314, -95.9372);
        // Location Denver = new Location(39.7392, -104.9903);
        Location Billund = new Location(55.7308, 9.1153);
        
        maf.addFilter(new DistanceFilter(Billund, 3000000));
        maf.addFilter(new PhraseFilter("any", "e"));
        maf.addFilter(new MagnitudeFilter(0.0, 5.0));
        
        for (QuakeEntry qe: list) {
            
            if (maf.satisfies(qe)) {
                filtered.add(qe);
            }
        }
        
        for (QuakeEntry qe: filtered) { 
            System.out.println(qe);
        }
        
        System.out.println("found " + filtered.size() + " quakes that match the criteria");
        System.out.println("and they are: " + maf.getName());
    }

}
