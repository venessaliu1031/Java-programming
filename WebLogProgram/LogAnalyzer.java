
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.text.DateFormat;  
import java.text.SimpleDateFormat; 
import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
    private ArrayList<LogEntry> records; 
     
    public LogAnalyzer() {
         
        // complete constructor
        records = new ArrayList<LogEntry>();
         
    }
        
    public void readFile(String filename) {
         
        FileResource fr = new FileResource(filename);
         
        for (String line: fr.lines()){
             
            WebLogParser parser = new WebLogParser();
             
            LogEntry le  = parser.parseEntry(line);
             
            records.add(le);
            
        }
         // complete method
    }
     
    public int countUniqueIPs() {
         
        ArrayList<String> uniqIPs = new ArrayList<String>();
         
        for (LogEntry le: records) {
             String ipAddr = le.getIpAddress();
             if (!uniqIPs.contains(ipAddr)){
                 uniqIPs.add(ipAddr);
             }
        }
            
         
        return uniqIPs.size();
    }
        
    public void printAllHigherThanNum(int num) {
        
        for (LogEntry le : records){
            int statusCode = le.getStatusCode();
            if (statusCode > num) {
                System.out.println(le);
            }
            
        }
        
        
    }
    
    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> ipsOnDay = new ArrayList<String>();
        for (LogEntry le: records) {
            DateFormat dateFormat = new SimpleDateFormat("MMM dd");  
            Date accessTime = le.getAccessTime();
            String strDate = dateFormat.format(accessTime); 
            if (strDate.equals(someday) && !ipsOnDay.contains(le.getIpAddress())){
                ipsOnDay.add(le.getIpAddress());
            }
            
        }
        
        
        return ipsOnDay;
    }
    
    public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> ipsInRange = new ArrayList<String>();
        for (LogEntry le : records){
            int statusCode = le.getStatusCode();
            String ip = le.getIpAddress();
            if (statusCode >= low && statusCode <= high 
                && !ipsInRange.contains(ip)) {
                ipsInRange.add(ip);
            }
            
        }
        return ipsInRange.size();
    }
    
    public HashMap<String, Integer> countVisitsPerIP () {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        counts.clear();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if(!counts.containsKey(ip)) {
                counts.put(ip, 1);
            } else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return counts;
    }
    
    public int maxVisitIP(HashMap<String, Integer> counts) {
        int maxCount = 0;
        
        for (String ip: counts.keySet()) {
            if (counts.get(ip) >= maxCount){
                maxCount = counts.get(ip);
            }
        }
        
        
        
        
        return maxCount;
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
        int maxCount = 0;
        
        for (String ip: counts.keySet()) {
            if (counts.get(ip) >= maxCount){
                maxCount = counts.get(ip);
            }
        }
        
        ArrayList<String> maxIPs = new ArrayList<String>();
        for (String ip: counts.keySet()) {
            if (counts.get(ip) == maxCount) {
                maxIPs.add(ip);
            }
        }
        return maxIPs;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> countsByDay = new HashMap<String, ArrayList<String>>();
        for (LogEntry le: records) {
            DateFormat dateFormat = new SimpleDateFormat("MMM dd");  
            Date accessTime = le.getAccessTime();
            String strDate = dateFormat.format(accessTime); 
            String ip = le.getIpAddress();
            if (!countsByDay.containsKey(strDate)){
                ArrayList<String> ipList = new ArrayList<String>();
                ipList.add(ip);
                countsByDay.put(strDate, ipList);
                
            } else {
                ArrayList<String> ipList = countsByDay.get(strDate);
                ipList.add(ip);
                countsByDay.put(strDate, ipList);
                
            }
            
        }
        return countsByDay;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> countsByDay) {
        String maxVisitDay = "";
        int maxVisit = 0;
        for (String date: countsByDay.keySet()) {
            int visits = countsByDay.get(date).size();
            if (visits > maxVisit) {
                maxVisit = visits;
                maxVisitDay = date;
            }
        }
        return maxVisitDay;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> countsByDay, String date) {
        ArrayList<String> ips = countsByDay.get(date);
        HashMap<String, Integer> ipCounts = new HashMap<String, Integer>();
        
        for (String ip : ips) {
            if (ipCounts.containsKey(ip)) {
                int count = ipCounts.get(ip);
                ipCounts.put(ip, count+1);
            } else {
                ipCounts.put(ip, 1);
            }
        }
        
        ArrayList<String> maxVisitIPs = iPsMostVisits(ipCounts);
        
        
        return maxVisitIPs;
    }
        
    public void printAll() {
        
        for (LogEntry le : records) {
            
            System.out.println(le);
        }
    }
     
     
}
