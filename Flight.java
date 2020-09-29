/*
 * Purpose: DSA Project - Flight class
 * Status:
 * Last Updated: 12/3/19 (Dylan Ott)
 * Submitted: 12/5/19
 * Comment:
 * @author: Dylan Ott
 * @author: Christopher Herras-Antig
 * @version: 2019.12.03
 */

public class Flight {

    private String flightNumber;
    private String destination;

    public Flight(String flightNumber, String destination)
    {
    	this.flightNumber = flightNumber;
    	this.destination = destination;
    }

    	public String getFlightNumber()
    {
    		return flightNumber;
    }

    public String getDestination()
    {
    	return destination;
    }
    
    public String toString()
    {
    	return ("Flight " + flightNumber + " -- Destination: " + destination);
    }
    
    


    //setters necessary? Is there any point where we would need to reset
    //data within the individual flight?
}
