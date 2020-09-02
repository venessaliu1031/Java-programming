
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;


public class Tester
{
    private LogAnalyzer la;
    
    public Tester () {
        la = new LogAnalyzer();
        la.readFile("weblog2_log");
    }
    
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        //la.readFile("weblog1_log");
        la.printAll();
        System.out.println("uniq IP address number: " + la.countUniqueIPs());
        int lowerLimit = 400;
        System.out.println("Log entrys with status code larger than " + lowerLimit + ":");
        la.printAllHigherThanNum(lowerLimit);
        String someday = "Sep 24";
        System.out.println("There are " + la.uniqueIPVisitsOnDay(someday).size() + " unique IP visits on " + someday + ":");
        for (String ip : la.uniqueIPVisitsOnDay(someday)){
            System.out.println(ip);
        }
        
        
        // complete method
    }
    
    public void testUniqIPsInRange() {
        int low = 200;
        int high = 299;
        System.out.println("Number of unique IP visit with status code in range " 
            + low + " to " + high + ": " + la.countUniqueIPsInRange(low, high));
       
    }
    
    
    public void testCounts() {
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println("There are " + counts.size() + " unique IPs in this log, and their counts are:");
        for (String ip: counts.keySet()) {
            System.out.println(ip + "\t" + counts.get(ip));
        }
     
    }
    
    
    
    public void testMaxVisit() {
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println("The maximum visits per IP is " + la.maxVisitIP(counts) + " times");
    }
    
    public void testIPsMostVisits() {
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        ArrayList<String> ips = la.iPsMostVisits(counts);
        System.out.println("Most visit IPs are:");
        for (String ip: ips) {
            System.out.println(ip + "\t" + counts.get(ip));
        }
        
    }
    
    public void testIPsByDay() {
        HashMap<String, ArrayList<String>> countsByDay = la.iPsForDays();
        System.out.println("IP address visits by date: ");
        for (String date: countsByDay.keySet()) {
            System.out.println(date + "\t" + countsByDay.get(date));
        }
        
    }
    
    public void testMostVisitDay() {
        HashMap<String, ArrayList<String>> countsByDay = la.iPsForDays();
        String mostVisitDay = la.dayWithMostIPVisits(countsByDay);
        System.out.print("Most visited day is " + mostVisitDay + " with " + countsByDay.get(mostVisitDay).size() + " times visits");
    }
    
    public void testIPMostVisitOnDay() {
        HashMap<String, ArrayList<String>> countsByDay = la.iPsForDays();
        String date = "Sep 30";
        ArrayList<String> ips = la.iPsWithMostVisitsOnDay(countsByDay, date);
        
        for (String ip: ips){
            System.out.println(ip);
        }
    }
    
    
    
}







