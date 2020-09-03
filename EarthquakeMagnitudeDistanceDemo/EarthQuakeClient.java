
import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        //TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;              
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {      
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDep, double maxDep) {
        ArrayList<QuakeEntry> filtered = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (qe.getDepth() > minDep && qe.getDepth() < maxDep){
                filtered.add(qe);
            }
        } 
        return filtered;
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase){
        ArrayList<QuakeEntry> filtered = new ArrayList<QuakeEntry>();

        for (QuakeEntry qe: quakeData){

            if (where.equals("start")){
                if (qe.getInfo().substring(0, phrase.length()).equals(phrase)){
                    filtered.add(qe);
                }
            }

            if (where.equals("end")){
                String title = qe.getInfo();
                if (title.substring(title.length()-phrase.length()).equals(phrase)){
                    filtered.add(qe);
                }
            }

            if (where.equals("any")){
                String title = qe.getInfo();
                if (title.contains(phrase)){
                    filtered.add(qe);
                }
            }
        }

        return filtered;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        /*
        for (QuakeEntry qe : list) {
        if (qe.getMagnitude() > 5.0) {
        System.out.println(qe);
        }
        }
         */
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : listBig) {
            System.out.println(qe); 
        }
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

    public void closeToMe() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());

        //Durham, NC
        //Location city = new Location(35.988, -78.907);
        //Bridgeport, CA
        Location city = new Location(38.17, -118.82);
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000*1000, city);
        for (int k=0; k< close.size(); k++) {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000 + " " + entry.getInfo());
        }
        System.out.println("found " + close.size() + " quakes that match the criteria.");

    }

    public void deepQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        ArrayList<QuakeEntry> listDeep = filterByDepth(list, -4000.0, -2000.0);
        for (QuakeEntry qe : listDeep) {
            System.out.println(qe); 
        }
        System.out.println("found " + listDeep.size() + " quakes that match the criteria.");
    }

    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        String phrase = "Can";
        String where = "any";
        ArrayList<QuakeEntry> phraseFiltered = filterByPhrase(list, where, phrase);
        for (QuakeEntry qe : phraseFiltered) {
            System.out.println(qe); 
        }
        System.out.println("found " + phraseFiltered.size() + " quakes that match the criteria.");
    }

    
}
