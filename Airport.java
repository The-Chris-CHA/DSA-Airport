/*
 * Purpose: DSA Project - Airport Class
 * Status: Complete and thoroughly tested
 * Last Updated: 12/3/19 (Dylan Ott)
 * Submitted: 12/5/19
 * Comment:
 * @author: Dylan Ott
 * @author: Christopher Herras-Antig
 * @version: 2019.12.03
 */

public class Airport {
	
	private ListArrayBasedPlus<Runway> runwayList;
	private ListArrayBasedPlus<Flight> reEntryPool;
	private ListArrayBasedPlus<Flight> reEntryPoolLanding;
	private int currentRunway = 0;
	private int numRunways;//used for iteration
	
	/*
	 * Creates a new Airport object, containing three ListArrayBasedPlus objects,
	 * all empty on instantiation.
	 */
	public Airport()
	{
		numRunways = 0;
		runwayList = new ListArrayBasedPlus<Runway>();
		reEntryPool = new ListArrayBasedPlus<Flight>();
		reEntryPoolLanding = new ListArrayBasedPlus<Flight>();
	}
	
	/*
	 * Method to check if the Airport contains any Runway Objects in runwayList
	 * 
	 * @return true if runwayList is empty, false otherwise
	 */
	public boolean isEmpty()
	{
		return numRunways == 0;
	}
	
	/*
	 * Getter method to get the number of runways in the runwayList
	 * 
	 * @return The number of runways in the runwayList
	 */
	public int size() {
		return numRunways;
	}
	
	/*
	 * Getter method that returns - as a ListArrayBasedPlus object -
	 * the collection of Runways.
	 * 
	 * @return The collection of Runways (as a ListArrayBasedPlus object)
	 */
	public ListArrayBasedPlus<Runway> getRunways()
	{
		return runwayList;
	}
	
	/*
	 * Getter method that returns - as a ListArrayBasedPlus object -
	 * the collection of Flights waiting to re-enter departure queues.
	 * 
	 * @return The collection of Flights waiting to re-enter departure queues (as a ListArrayBasedPlus object)
	 */
	public ListArrayBasedPlus<Flight> getReEntryPool()
	{
		return reEntryPool;
	}
	
	/*
	 * Getter method that returns - as a ListArrayBasedPlus object -
	 * the collection of Flights waiting to re-enter arrival queues.
	 * 
	 * @return The collection of Flights waiting to re-enter arrival queues (as a ListArrayBasedPlus object)
	 */
	public ListArrayBasedPlus<Flight> getReEntryPoolLanding()
	{
		return reEntryPoolLanding;
	}
	
	/*
	 * This method removes a Runway object from the List, and
	 * decrements numRunways to keep accurate count.
	 */
	public void closeRunway(int runwayIndex)
	{
		//"placeholder" method to ensure numRunways is decremented
		runwayList.remove(runwayIndex);
		numRunways--;
	}

	
	/*
	 * This method takes the runway number to be added as a parameter
	 * and searches all existing Runways for a Runway with that number.
	 * If no match is found, a new Runway is created and added to the
	 * List. If a match is found, it is not added.
	 * 
	 * @param runwayNumber The new Runway's ID number
	 * @return true if the runway was added, false otherwise
	 */
	public boolean addRunway(String runwayNumber)
	{
		boolean added = false;
		if(numRunways == 0)
		{
			runwayList.add(runwayList.numItems, new Runway(runwayNumber));
			added = true;
			numRunways++;
		}
		else
		{
			boolean exists = false;
			for(int i = 0; i < numRunways; i++)
			{
				if(runwayList.get(i).getRunwayNumber().equalsIgnoreCase(runwayNumber))
				{//runway already exists
					exists = true;
				}
			}
			
			if(!exists)
			{
				runwayList.add(runwayList.numItems, new Runway(runwayNumber));
				added = true;
				numRunways++;
			}
		}
		
		//quickSortRunway(0, runwayList.size()-1);
		return added;
	}
	
	/*
	 * Method to add a new Flight to the takeOff queue. 
	 * 
	 * @param flightNum The Flight's ID number
	 * @param destination The Flight's destination
	 * @param runway The Runway to add the new Flight to
	 * 
	 * @return true if the Flight was added, false otherwise
	 */
	public boolean addPlaneTakeOff(String flightNum, String destination, String runway) {
		Flight addedFlight = new Flight(flightNum, destination);
		
		int designatedRunway = searchRunway(runway);
		if (designatedRunway > -1) {
			runwayList.get(designatedRunway).addFlight(addedFlight);
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	 * Method to add a new Flight to the landing queue. 
	 * 
	 * @param flightNum The Flight's ID number
	 * @param destination The Flight's destination
	 * @param runway The Runway to add the new Flight to
	 * 
	 * @return true if the Flight was added, false otherwise
	 */
	public boolean addPlaneLanding(String flightNum, String runway) {
		Flight addedFlight = new Flight(flightNum, "DS Airport");
		
		int designatedRunway = searchRunway(runway);
		if (designatedRunway > -1) {
			runwayList.get(designatedRunway).addFlightOrbit(addedFlight);
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	 * Method to add a new Flight to the takeOff re-entry pool. This
	 * dequeues the Flight from its current queue and then QuickSorts
	 * the pool afterwards, for efficient searching later.
	 * 
	 * @param flightIns The Flight to be added
	 */
	public void addToReEntryPool (Flight flightIns) {
		reEntryPool.add(reEntryPool.size(), flightIns);
		runwayList.get(currentRunway).takeoff(); // Dequeue from takeoff
		qSortFlights(reEntryPool, 0, reEntryPool.size()-1);
		currentRunway++;
		if (currentRunway > (numRunways-1)) {
			currentRunway = 0;
		}
	}
	
	
	/*
	 * Method to add a new Flight to the arrival re-entry pool. This
	 * dequeues the Flight from its current queue and then QuickSorts
	 * the pool afterwards, for efficient searching later.
	 * 
	 * @param flightIns The Flight to be added
	 */
	public void addToReEntryPoolLanding(Flight flightIns)
	{
		reEntryPoolLanding.add(reEntryPoolLanding.size(), flightIns);
		runwayList.get(currentRunway).land(); // Dequeue from landings
		qSortFlights(reEntryPoolLanding, 0, reEntryPoolLanding.size() - 1);
		currentRunway++;
		if (currentRunway > (numRunways-1)) {
			currentRunway = 0;
		}
	}
	
	/*
	 * Method to return the next plane available for take off.
	 * This method searches the List of Runways and finds the
	 * first non-empty departure queue. It then returns the 
	 * Flight at the front of that queue.
	 * 
	 * @return The Flight at the front of the first non-empty departure queue, null if no such Flight exists
	 */
	public Flight nextTakeOffPlane() {
		boolean planeFound = false;
		int increments = 0;
		
		while (planeFound == false && increments < numRunways) {
			if (runwayList.get(currentRunway).displayWaiting() != null) {
				return runwayList.get(currentRunway).displayWaiting();
			}
			else {
				increments++;
				if (currentRunway >= (numRunways-1)) {
					currentRunway = 0;
				}
				else {
					currentRunway++;
				}
			}
		}
		
		// No runway with planes for takeoff
		return null;
	}
	
	/*
	 * Method to return the next plane available for arrival.
	 * This method searches the List of Runways and finds the
	 * first non-empty arrival queue. It then returns the 
	 * Flight at the front of that queue.
	 * 
	 * @return The Flight at the front of the first non-empty arrival queue, null if no such Flight exists
	 */
	public Flight nextLandingPlane() {
		boolean planeFound = false;
		int increments = 0;
		
		while (planeFound == false && increments < numRunways) {
			if (runwayList.get(currentRunway).displayWaitingLanding() != null) {
				return runwayList.get(currentRunway).displayWaitingLanding();
			}
			else {
				increments++;
				if (currentRunway >= (numRunways-1)) {
					currentRunway = 0;
				}
				else {
					currentRunway++;
				}
			}
		}
		
		// No runway with planes for landing
		return null;
	}
	
	/*
	 * This method allows a plane to take off.
	 * It calls the takeoff() method of the Runway
	 * class to do so. It then returns the Runway
	 * from which this plane took off.
	 * 
	 * @return The Runway that the Flight took off from
	 */
	public Runway planeTakeOff() {
		int saveRunway = currentRunway;
		runwayList.get(saveRunway).takeoff();
		currentRunway++;
		if (currentRunway >= numRunways) {
			currentRunway = 0;
		}
		return runwayList.get(saveRunway);
	}
	
	/*
	 * This method allows a plane to take off.
	 * It calls the land() method of the Runway
	 * class to do so. It then returns the Runway
	 * on which this plane landed.
	 * 
	 * @return The Runway that the Flight landed on
	 */
	public Runway planeLand()
	{
		int saveRunway = currentRunway;
		runwayList.get(saveRunway).land();
		currentRunway++;
		if (currentRunway >= numRunways) {
			currentRunway = 0;
		}
		return runwayList.get(saveRunway);
	}
	
	/*
	 * This method ensures that a Flight exists by searching the
	 * appropriate pool. 
	 * 
	 * @param flight The Flight ID to search for
	 * @param isLanding The identifier for whether the Flight is in the departure or arrival re-entry pool
	 * 
	 * @return The index in the Re-Entry pool of the Flight, or -1 if it does not exist
	 */
	public int validateFlight(String flight, boolean isLanding) {
		int result = -1;
		
		if (!isLanding) 
			result = searchFlight(reEntryPool, 0, reEntryPool.size()-1, flight);
		
		else 
			result = searchFlight(reEntryPoolLanding, 0, reEntryPoolLanding.size()-1, flight);
		
		if (result != -1) {
			return result;
		}
		else {
			return -1;
		}
	}
	
	/*
	 * This method re-assigns a flight to a departure queue.
	 * The Flight is removed from the pool and enqueued in the
	 * appropriate departure queue.
	 * 
	 * @param flightIndex The index in the Re-Entry pool of the Flight
	 * @param runwayIndex The index in the Runway List of the Runway to add the Flight to
	 */
	public void reEnterRunway(int flightIndex, int runwayIndex) {
		Flight addedFlight = reEntryPool.get(flightIndex);
		reEntryPool.remove(flightIndex);
		runwayList.get(runwayIndex).addFlight(addedFlight);
	}
	
	/*
	 * This method re-assigns a flight to an arrival queue.
	 * The Flight is removed from the pool and enqueued in the
	 * appropriate arrival queue.
	 * 
	 * @param flightIndex The index in the Re-Entry pool of the Flight
	 * @param runwayIndex The index in the Runway List of the Runway to add the Flight to
	 */
	public void reEnterRunwayLanding(int flightIndex, int runwayIndex) {
		Flight addedFlight = reEntryPoolLanding.get(flightIndex);
		reEntryPoolLanding.remove(flightIndex);
		runwayList.get(runwayIndex).addFlightOrbit(addedFlight);
	}
	
	// Exhaustive search for runway
	public int searchRunway(String runwayKey) {
		for (int x = 0; x < numRunways; x++) {
			if (runwayList.get(x).getRunwayNumber().equalsIgnoreCase(runwayKey)) {
				return x;
			}
		}
		// Failed to find
		return -1;
	}
	
	// Begin flight search and sorts
	// Begin bSearch for flights
	public int searchFlight(ListArrayBasedPlus<Flight> list, int left, int right, String flightSearch) {
		if (right >= left) {
			int mid = left + (right - 1) / 2;
		
			if (((list.get(mid)).getFlightNumber()).equalsIgnoreCase(flightSearch)) {
				return mid;
			}
			
			if (((list.get(mid)).getFlightNumber()).compareToIgnoreCase(flightSearch) > 0) {
				return searchFlight(list, left, mid-1, flightSearch);
			}
			else {
				return searchFlight(list, mid+1, right, flightSearch);
			}
		
		}
		return -1;
	} // End bSearch for flights
	
	
	// Begin Quicksort for flights // 
	public void qSortFlights(ListArrayBasedPlus<Flight> list, int low, int high) {
		if (low < high) {
			int part = partFlights(list, low, high);
			
			qSortFlights(list, low, part-1);
			qSortFlights(list, part+1, high);
		}
		
	}
	
	public int partFlights(ListArrayBasedPlus<Flight> list, int low, int high) {
		String pivot = list.get(high).getFlightNumber();
		int x = (low -1);
		for (int y = low; y<high; y++) {
			if ((list.get(y).getFlightNumber()).compareToIgnoreCase(pivot) < 0) {
				x++;
				
				Flight temp = list.get(x);
				
				list.set(x, list.get(y));
				list.set(y, temp);
			}
		}
		
		Flight temp2 = list.get(x+1);
		list.set(x+1, list.get(high));
		list.set(high, temp2);
		return x+1;
	}
	// End quicksort for Flights
	
	
}