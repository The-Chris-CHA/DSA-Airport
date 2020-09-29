/*
 * Purpose: DSA Project - Runway class
 * Status:
 * Last Updated: 12/5/19 (Chris Herras-Antig)
 * Submitted: 12/5/19
 * Comment:
 * @author: Dylan Ott
 * @author: Christopher Herras-Antig
 * @version: 2019.12.05
 */

public class Runway {

    private QueueRAB<Flight> takeOffQueue;
    private QueueRAB<Flight> landingQueue;
    private String runwayNumber;

    public Runway(String number)
    {
    	runwayNumber = number;

    	takeOffQueue = new QueueRAB<Flight>();
		landingQueue = new QueueRAB<Flight>();
    }
    
    public boolean takeOffIsEmpty() {
    	return takeOffQueue.isEmpty();
    }

    public boolean landingIsEmpty() {
    	return landingQueue.isEmpty();
    }
    
    public String getRunwayNumber()
    {
    	return runwayNumber;
    }

    public QueueRAB<Flight> getTakeOffQueue() {
    	return takeOffQueue;
    }
    
    public QueueRAB<Flight> getLandingQueue() {
    	return landingQueue;
    }
    
    public void takeoff()
    {
    	//plane takes off
    	takeOffQueue.dequeue();
    }
    

    public void addFlight(Flight newFlight)
    {
    	//add new flight to end of landing queue
    	takeOffQueue.enqueue(newFlight);
    }
    
    public void land()
    {
    	//Flight lands
    	landingQueue.dequeue();
    }
    
    public void addFlightOrbit(Flight newFlight) 
    {
    	// add new flight to end of landing queue
    	landingQueue.enqueue(newFlight);
    }

    public Flight displayWaiting()
    {
    	try {
    		Flight output = takeOffQueue.peek();
    		return output;
    	}
    	catch (Exception e) {
    		// Nothing in there. Queue is empty.
    		return null;
    	}
    }
    public Flight displayWaitingLanding()
    {
    	try {
    		Flight output = landingQueue.peek();
    		return output;
    	}
    	catch (Exception e) {
    		// Nothing in there. Queue is empty.
    		return null;
    	}
    }
    
    
    
    public String displayAllWaiting()
    {
    	try {
    		String output = takeOffQueue.toString();
    		return output;
    	}
    	catch (Exception e) {
    		// Nothing in there. Queue is empty.
    		return null;
    	}
    }
    
    
    public String displayAllWaitingLanding()
    {
    	try {
    		String output = landingQueue.toString();
    		return output;
    	}
    	catch(Exception e) {
    		//do nothing
    		return null;
    	}
    	
    }
}
