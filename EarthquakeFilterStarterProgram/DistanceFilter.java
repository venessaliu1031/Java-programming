
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    
    private double maxDist;
    private Location loc;
    
    public DistanceFilter(Location l, double dist) {
        loc = l;
        maxDist = dist;
        
        
    }
    
    public boolean satisfies(QuakeEntry qe) { 
        double distance = qe.getLocation().distanceTo(loc);
        return (distance < maxDist);
    }
    
    public String getName() {
        return "Distance";
    }

}
