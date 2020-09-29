/*
 * Purpose: Data Structures and Algorithms PROJECT - Driver
 * Status: Complete and thoroughly tested
 * Last Updated: 12/5/19 (Chris H-A)
 * Submitted: 12/5/19
 * Comment: JavaDoc and sample runs attached
 * @author: Dylan Ott
 * @author: Christopher Herras-Antig
 * @version: 2019.12.05
 */
import java.io.*;

public class Driver {
    
	//setup input and counter variables
	static BufferedReader input = new BufferedReader (new InputStreamReader(System.in));
	static String inputStr, inputVal;
	static int takeOffs = 0;
	static int landings = 0;
	
	//begin main
	public static void main(String[] args) throws IOException, InstantiationException,
	ClassNotFoundException, IllegalAccessException
	{
		// Init
		boolean exitLoop = false;
		Airport airport = new Airport();
		
		//Init Runways
		System.out.print("Enter the number of runways: ");
		inputVal = input.readLine();
		System.out.println(inputVal);
		
		for (int x = 1; x <= Integer.parseInt(inputVal); x++) { 
			boolean validInput = false;
			
			while (!validInput) {
				System.out.print("Enter the name of runway number " + x + ": ");
				inputStr = input.readLine();
				System.out.println(inputStr);
				
				if(airport.addRunway(inputStr))
		    	{
		    		validInput = true;
		    	}
		    	else
		    	{
		    		System.out.println("Could not add runway -- Runway already exists!");
		    	}
			}
		}
		
		
		// Menu Option Display
		String menuOptions[] = {
				"End the program",
				"Plane enters the system (Takeoff)",
				"Plane enters the system (Landing)",
				"Plane takes off",
				"Plane lands",
				"Plane is allowed to re-enter a runway",
				"Plane is allowed to re-enter a landing circuit",
				"Runway opens",
				"Runway closes",
				"Display info about planes waiting to take off",
				"Display info about planes waiting to land",
				"Display info about planes waiting to be allowed to re-enter a runway",
				"Display info about planes waiting to be allowed to re-enter landing circuit",
				"Display number of planes that have taken off",
				"Display number of planes that have landed"
		};
		ModularMenu menu = new ModularMenu();
		menu.startupPrompt(0, menuOptions);
		
		
		
		while (exitLoop != true) {
			System.out.print("Make your selection now: ");
			inputStr = input.readLine();
			System.out.println(inputStr);
			
			switch (inputStr) {
			case "0":
				System.out.println("Signing off...");
				exitLoop = exitProg();
				break;
				
			case "1": 
				// Plane enters
				planeEnters(airport);
				break;
				
			case "2":
				// Plane enters system for landing
				planeEntersOrbit(airport);
				break;
				
			case "3":
				// Plane takes off from airport
				takeOff(airport);
				break;
				
			case "4":
				// Plane lands at airport
				land(airport);
				break;
				
			case "5":
				planeRe_Enters(airport);
				break;
				
			case "6":
				//Plane returns to landing approach
				planeRe_EntersOrbit(airport);
				break;
				
			case "7":
				// runway opens
				runwayOpen(airport);
				break;
				
			case "8":
				//runway closes
				runwayClose(airport);
				break;
				
			case "9":
				//display all planes ready for takeOff
				displayWaitingToTakeOff(airport);
				break;
				
			case "10":
				//Display info of waiting to land
				displayWaitingToLand(airport);
				break;
				
			case "11":
				// Display info on planes waiting to re-enter a runway
				displayWaitingToRe_Enter(airport);
				break;
				
			case "12":
				//Display info about planes waiting to re-enter landing circuit.
				displayWaitingToRe_EnterLanding(airport);
				break;
				
			case "13":
				//Display # of takeoffs
				displayTakeOffs();
				break;
				
			case "14":
				//Display # of landings
				displayLandings();
				break;
				
			default:
				System.out.println("Invalid input.\n");
				break;
			} // end switch
		} // end input while loop
	
		// Should be safe exit
		System.out.println("Exiting program...Good Bye");
		System.exit(0);
		
		
	}//end main
	
	/*
	 * A simple method to end the program when menu option 0 is selected
	 * 
	 * @return true at all times
	 */
	private static boolean exitProg() {
		return true;
	}
    
    //Option 1 - Plane enters
	/*
	 * Asks the user for 3 inputs - a flight number, destination,
	 * and a runway - and adds a flight to the system with those
	 * parameters. If there is no runway which matches the inputted
	 * runway number, then no flight is added, and the user is notified
	 * before being returned to the menu.
	 * 
	 * @param theAirport The Airport whose system will add the new flight
	 */
    public static void planeEnters(Airport theAirport) throws IOException
    {
    	//probe user
    	System.out.print("Specify flight number: ");
    	String flightNumber = input.readLine();
    	System.out.println(flightNumber);
    	
    	System.out.print("Specify a destination: ");
    	String destination = input.readLine();
    	System.out.println(destination);
    	
    	// Add data validation
    	boolean validInput = false;
    	while (!validInput) {
	    	System.out.print("Specify a runway: ");
	    	String runway = input.readLine();
	    	System.out.println(runway);
	    	
	    	
	    	
	    	if(theAirport.addPlaneTakeOff(flightNumber, destination, runway) == true)
	    	{
	    		//takeOff Flight added, inform user
	    		System.out.println("Flight " + flightNumber + " is now waiting for takeoff on runway " + runway + "\n");
	    		validInput = true;
	    	}
	    	else
	    	{
	    		//runway did not exist, exit method
	    		System.out.println("Could not add flight -- Ensure runway exists.\n");
    	}
    	}
    }
    
  //Option 2 - Plane enters orbit
  	/*
  	 * Asks the user for 2 inputs - a flight number and a runway. 
  	 * Adds a flight to the system with those parameters.
  	 * If there is no runway which matches the input runway number, 
  	 * then no flight is added, and the user is notified
  	 * before being returned to the menu.
  	 * 
  	 * @param theAirport The Airport whose system will add the new flight
  	 */
      public static void planeEntersOrbit(Airport theAirport) throws IOException
      {
    	  //probe user
      	System.out.print("Specify flight number: ");
      	String flightNumber = input.readLine();
      	System.out.println(flightNumber);
      	
      	System.out.print("Specify a runway: ");
      	String runway = input.readLine();
      	System.out.println(runway);
      	
      	
      	if(theAirport.addPlaneLanding(flightNumber, runway) == true)
      	{
      		//landing Flight added, inform user
      		System.out.println("Flight " + flightNumber + " is now slotted for landing on runway " + runway + "\n");
      	}
      	else
      	{
      		//runway did not exist, exit method
      		System.out.println("Could not add flight to track -- Ensure runway exists.\n");
      	}
      }
    
    //Option 3 - Plane takes off
    /*
     * The first available runway in the system with a plane in its takeoff
     * queue may takeoff. This method asks the user for clearance before
     * dequeue-ing. If clearance is given, the flight leaves the runway and
     * the Airport system. If not, the flight is still dequeued from the 
     * runway takeoff queue, but is instead added to a pool where it will
     * wait to re-enter the takeoff queue from the back.
     * 
	 * @param theAirport The Airport from which the flight will take off
     */
    public static void takeOff(Airport theAirport) throws IOException
    {
    	if (theAirport.isEmpty() != true) {
    		//runways exist
    		
    		if (theAirport.nextTakeOffPlane() != null) {
    			//takeOff Flight exists
    			
    			//probe user for clearance
    			String flightNum = theAirport.nextTakeOffPlane().getFlightNumber();
    			System.out.print("Is flight " + flightNum + " cleared for takeoff (Y/N): ");
    			String clearance = input.readLine();
    			System.out.println(clearance);
    			
    			
    			if (clearance.equalsIgnoreCase("Y")) {
    				//cleared, take off
    				System.out.println("Flight " + flightNum + " has now taken off from runway " + theAirport.planeTakeOff().getRunwayNumber() + "\n");
    				takeOffs++;
    			}
    			else {
    				//denied, add to pool
    				theAirport.addToReEntryPool(theAirport.nextTakeOffPlane());
    				System.out.println("Flight " + flightNum + " is now waiting to be allowed to re-enter a runway\n");
    			}
    		}
    		else {
    			//no takeOff Flights
    			System.out.println("No planes available for takeoff.\n");
    		}
    	}
    	else {
    		//no runways
    		System.out.println("The airport has no runways.\n");
    	}
    }
    
    //Option 4 - Plane lands
    /*
     * The first available runway with a plane waiting to land may
     * give the plane clearance to land. This method asks the user for
     * clearance prior to landing. If clearance is given, the plane lands
     * and is dequeued from the runway's landing queue, and exits the
     * Airport system. If not, the plane is still dequeued, but is instead
     * added to a pool where it will wait to re-enter a landing queue
     * from the back.
     * 
     * @param theAirport The Airport where the flight will land
     */
    public static void land(Airport theAirport) throws IOException
    {
    	if(theAirport.isEmpty()) {
    		//no runways, cannot land
    		System.out.println("No runways to land on!");
    	}
    	else
    	{
    		if(theAirport.nextLandingPlane() != null)
    		{
    			//landing Flight exists
    			
    			//probe user for clearance
    			String flightNum = theAirport.nextLandingPlane().getFlightNumber();
    			System.out.println("Is flight " + flightNum + " cleared to land (Y/N): ");
    			String clearance = input.readLine();
    			System.out.println(clearance);
    			
    			if(clearance.equalsIgnoreCase("Y"))
    			{
    				//cleared, land
    				System.out.println("Flight " + flightNum + " has now landed on runway " + theAirport.planeLand().getRunwayNumber());
    				landings++;
    			}
    			else
    			{
    				//denied, add to pool
    				theAirport.addToReEntryPoolLanding(theAirport.nextLandingPlane());
    				System.out.println("Flight " + flightNum + " is now waiting to re-enter a landing queue");
    			}
    		}
    		else
    		{
    			//no landing Flight exists
    			System.out.println("No planes waiting to land!");
    		}
    	}
    }
    
    //Option 5 - Plane re-enters runway
    /*
     * A specified plane from the re-entry pool is allowed to queue up for
     * takeoff at a specified runway. The user is asked for both the flight
     * number and the runway to assign it to. Flights may be assigned to 
     * different runways than they were when they entered the system.
     */
    public static void planeRe_Enters(Airport theAirport) throws IOException
    {
    	if (theAirport.getReEntryPool().isEmpty()) {
    		//no runways, cannot re-enter
    		System.out.println("There are no planes waiting for clearance!\n");
    	}
    	
    	else {
    		//probe user for ID
    		System.out.print("Enter flight number: ");
    		inputVal = input.readLine();
    		System.out.println(inputVal);
    		
    		int flightIndex = theAirport.validateFlight(inputVal, false);
    		if (flightIndex != -1) {
    			//Flight exists in pool
    			
    			//probe for Runway
    			System.out.print("Enter runway to reenter: ");
        		inputStr = input.readLine();
        		System.out.println(inputStr);
    			int runwayIndex = theAirport.searchRunway(inputStr);
    			
    			if (runwayIndex != -1) {
    				//runway exists, add to takeOff queue
    				theAirport.reEnterRunway(flightIndex, runwayIndex);
    				System.out.println("Flight " + inputVal + " is now waiting for takeoff on runway " + inputStr + "\n");
    			}
    			
    			else {
    				//runway does not exist
    				System.out.println("No such runway to reenter.\n");
    			}
    			
    		}
    		else {
    			//Flight does not exist
    			System.out.println("No such flight awaiting clearance.\n");
    		}
    	}
    }
    
    //Option 6 - Plane re-enters landing 
    /*
     * A specified plane from the re-entry pool is allowed to queue up for
     * landing at a specified runway. The user is asked for both the flight
     * number and the runway to assign it to. Flights may be assigned to 
     * different runways than they were when they entered the system.
     */
    public static void planeRe_EntersOrbit(Airport theAirport) throws IOException
    {
    	if (theAirport.getReEntryPoolLanding().isEmpty()) {
    		//no runways
    		System.out.println("There are no planes waiting for clearance!\n");
    	}
    	
    	else {
    		//probe user for ID
    		System.out.print("Enter flight number: ");
    		inputVal = input.readLine();
    		System.out.println(inputVal);
    		
    		int flightIndex = theAirport.validateFlight(inputVal, true);
    		if (flightIndex != -1) {
    			//Flight exists in pool
    			
    			//probe for Runway
    			System.out.print("Enter runway to reenter: ");
        		inputStr = input.readLine();
        		System.out.println(inputStr);
    			int runwayIndex = theAirport.searchRunway(inputStr);
    			
    			if (runwayIndex != -1) {
    				//runway exists, add to landingQueue
    				theAirport.reEnterRunwayLanding(flightIndex, runwayIndex);
    				System.out.println("Flight " + inputVal + " has re-entered the circuit for runway " + inputStr + "\n");
    			}
    			
    			else {
    				//runway does not exist
    				System.out.println("No such runway to reenter.\n");
    			}
    			
    		}
    		else {
    			//Flight does not exist
    			System.out.println("No such flight awaiting clearance.\n");
    		}
    	}
    }
    
    //Option 7 - Runway Opens
    /*
     * Opens a new runway in the Airport system. If the runway ID already
     * exists, the runway is not added (see Airport.addRunway). This method
     * asks the user for input to specify the new runway's ID.
     * 
     * @param theAirport The Airport to add the runway
     */
    public static void runwayOpen(Airport theAirport) throws IOException
    {
   
    	//check if runway was added
    	boolean validInput = false;
    	//iterate until valid input is given
		while (!validInput) {
			System.out.print("Enter the name of new runway: ");
			inputStr = input.readLine();
			System.out.println(inputStr);
			
			if(theAirport.addRunway(inputStr))
	    	{
				//runway was added, inform user
	    		System.out.println("Runway " + inputStr + " has opened.\n");
	    		validInput = true;
	    	}
	    	else
	    	{
	    		//invalid input, inform and loop
	    		System.out.println("Runway " + inputStr + " already exists, please choose another name.\n");
	    	}
		}
    }
    
    //Option 8 - Runway Closes
    /*
     * Closes a runway in the Airport system. This method asks the user
     * for input to specify the runway's ID. If the runway cannot be found,
     * then the user is informed before returning to the menu. If the runway
     * is found, the user is asked to provide new runways for all current flights
     * (both takeoff and landing) in the closing runway. User cannot re-assign
     * these flights to the closing runway.
     * 
     *  @param theAirport The Airport containing the runway to close
     */
    public static void runwayClose(Airport theAirport) throws IOException
    {
    	if(theAirport.isEmpty()) {
    		//no runways
    		System.out.println("No runways to close!");
    	}
    	else {
    		//probe for Runway to close
    		boolean validInput = false;
    		while (!validInput) {
	    		System.out.print("Closing runway...\nEnter runway ID: ");
	        	String id = input.readLine();
	        	System.out.println(id);
	        	
	        	//get Runway's index in List
	        	int toCloseIndex = theAirport.searchRunway(id);
	        	
	        	if(toCloseIndex == -1)
	        	{
	        		//unsuccessful search, no runway to remove
	        		System.out.println("Runway " + id + " does not exist!");
	        	}
	        	else
	        	{
	        		validInput = true;
	        		//success, begin removal process
	        		Runway toClose = theAirport.getRunways().get(toCloseIndex);
	        		
	        		//get departures and arrivals to iterate through and re-assign
	        		QueueRAB<Flight> takeOffs = toClose.getTakeOffQueue();
	        		QueueRAB<Flight> landings = toClose.getLandingQueue();
	        		
	        		Flight tempTakeOff = takeOffs.dequeue();
	        		Flight tempLanding = landings.dequeue();
	        		
	        		//reassign all takeoffs
	        		while(tempTakeOff != null)
	        		{
	        			//probe for runway
	        			System.out.print("Which runway would you like to re-assign flight " + tempTakeOff.getFlightNumber() + " to for take-off? : ");
	        			String newRunway = input.readLine();
	        			System.out.println(newRunway);
	        			
	        			int tempRunwayIndex = theAirport.searchRunway(newRunway);
	        			
	        			if(tempRunwayIndex != -1)
	        			{
	        				//runway exists
	        				if(!(newRunway.equalsIgnoreCase(toClose.getRunwayNumber())))
	        				{
	        					//runway is valid
	        					Runway tempRunway = theAirport.getRunways().get(tempRunwayIndex);
	        					tempRunway.addFlight(tempTakeOff);
	        					
	        					System.out.println("Flight " + tempTakeOff.getFlightNumber() + " is now awaiting takeoff on runway " + newRunway);
	        					
	        					//only advance on valid runway
	        					tempTakeOff = takeOffs.dequeue();
	        				}
	        				else
	        				{
	        					//closing runway input
	        					System.out.println("This is the runway being closed!");
	        				}
	        			}
	        			else
	        			{
	        				//phantom runway input
	        				System.out.println("No such runway!");
	        			}
	        		}
	        		
	        		//reassign all landings
	        		while(tempLanding != null)
	        		{
	        			//probe for runway
	        			System.out.print("Which runway would you like to re-assign flight " + tempTakeOff.getFlightNumber() + " to for landing? : ");
	        			String newRunway = input.readLine();
	        			System.out.println(newRunway);
	        			
	        			int tempRunwayIndex = theAirport.searchRunway(newRunway);
	        			
	        			if(tempRunwayIndex != -1)
	        			{
	        				//runway exists
	        				if(!(newRunway.equalsIgnoreCase(toClose.getRunwayNumber())))
	        				{
	        					//runway is valid
	        					Runway tempRunway = theAirport.getRunways().get(tempRunwayIndex);
	        					tempRunway.addFlight(tempLanding);
	        					
	        					System.out.println("Flight " + tempLanding.getFlightNumber() + " is now awaiting takeoff on runway " + newRunway);
	        					
	        					//only advance on valid runway
	        					tempLanding = landings.dequeue();
	        				}
	        				else
	        				{
	        					//closing runway input
	        					System.out.println("This is the runway being closed!");
	        				}
	        			}
	        			else
	        			{
	        				//phantom runway input
	        				System.out.println("No such runway!");
	        			}
	        		}
	        		
	        		//finally, remove runway from List
	        		theAirport.closeRunway(toCloseIndex);
	        	}
	        }
    	} 
    }
    
    //Option 9 - Display waiting to take off
    /*
     * Prints to the terminal all flights waiting to take off in all
     * runways. This method only displays those flights which would take
     * off should that runway be the next available runway, NOT all flights
     * in the Airport system which are flagged for takeoff.
     * 
     *  @param theAirport The Airport containing the takeoff runways
     */
    public static void displayWaitingToTakeOff(Airport theAirport)
    {
    	if(theAirport.isEmpty())
    	{
    		//no runways, cannot display
    		System.out.println("No runways, no planes waiting to take off!");
    	}
    	else
    	{
    		//iterate through runways
    		for(int i = 0; i < theAirport.size(); i++)
        	{
        		String runwayName = theAirport.getRunways().get(i).getRunwayNumber();
        		if (theAirport.getRunways().get(i).takeOffIsEmpty()) {
        			//runway has no departures
        			System.out.println("No planes are waiting for takeoff on runway " + runwayName +"!\n");
        		}
        		else {
        			//display
        			System.out.println("These planes are waiting for takeoff on runway " + runwayName);
        			System.out.println(theAirport.getRunways().get(i).displayAllWaiting());
        		}
        		
        	}
    	}
    	
    }
    
    //Option 10 - Display waiting to land
    /*
     * Prints to the terminal all flights waiting to take off
     * in all runways. This method only displays those flights which
     * would land should that runway be the next available runway, NOT
     * all flights which are flagged for landing.
     * 
     *  @param theAirport The Airport containing the landing runways
     */
    public static void displayWaitingToLand(Airport theAirport)
    {
    	if(theAirport.isEmpty())
    	{
    		//no runways
    		System.out.println("No runways, no planes waiting to land!");
    	}
    		for(int i = 0; i < theAirport.size(); i++)
    		{
    			//iterate through runways
    			String runwayName = theAirport.getRunways().get(i).getRunwayNumber();
    			
    			if(theAirport.getRunways().get(i).landingIsEmpty())
    			{
    				//runway has no arrivals
    				System.out.println("No planes waiting to land on runway " + runwayName + "!\n");
    			}
    			else
    			{
    				//display
    				System.out.println("These planes are waiting to land on runway " + runwayName);
    				System.out.println(theAirport.getRunways().get(i).displayAllWaitingLanding());
    			}
    		}
    }
    
    //Option 11 - Display waiting to re-enter
    /*
     * Prints to the terminal all flights in the Re-Entry pool.
     * 
     *  @param theAirport The Airport containing the pool
     */
    public static void displayWaitingToRe_Enter(Airport theAirport)
    {
    	if(theAirport.getReEntryPool().isEmpty()) {
    		//pool empty
    		System.out.println("No planes waiting to re-enter runways!\n");
    	}
    	else {	
    		//display
    		System.out.println("All planes waiting to re-enter runways -- ");
    		System.out.println(theAirport.getReEntryPool().toString());
    	}
    }
    
    //Option 12 - Display waiting to re-enter landing
    /*
     * Prints to the terminal all flights in the Re-EntryLanding pool.
     * 
     *  @param theAirport The Airport containing the pool
     */
    public static void displayWaitingToRe_EnterLanding(Airport theAirport)
    {
    	if(theAirport.getReEntryPoolLanding().isEmpty()) {
    		//pool empty
    		System.out.println("No planes waiting to re-enter circuit for landing!\n");
    	}
    	else
    	{
    		//display
    		System.out.println("All planes waiting to re-enter landing circuits -- ");
        	System.out.println(theAirport.getReEntryPoolLanding().toString());
    	}
    	
    }
    
    //Option 13 - Display take offs
    /*
     * Prints to the terminal the number of take offs that have been
     * successful from this Airport.
     * 
     */
    public static void displayTakeOffs()
    {
    	System.out.println(takeOffs + " flights have been cleared for take off and left the airport.\n");
    }
    
    //Option 14 - Display landings
    /*
     * Prints to the terminal the number of landings that have been
     * successful from this Airport.
     * 
     */
    public static void displayLandings()
    {
    	System.out.println(landings + " flights have been cleared for landing and landed at this airport.\n");
    }


    
    

}
