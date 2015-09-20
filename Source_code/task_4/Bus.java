package hw_4;
/**
 * A simple class which creates a bus object with accesses to the capacity, route, nextStop, toNextStop. To
 * represent the passenger inside the bus, a passenger queue will be created and all dynamic methods will be
 * available for further edition. <p>
 * Zhaoqi Li<p>
 * 109051837<p>
 * Assignment 4 task 3<p>
 * CSE 214 R05<p>
 * Vyassa Baratham (Recitation TA)<p>
 * Ke Ma (Grading TA)<p>
 */
public class Bus {
	/**
	 * Data Field
	 *<dt> capacity </dt> a static integer valuable which represents the maximum number of passengers the bus is able to carry.<p>
	 *<dt> route </dt> an integer value which determines the type of the bus. 0 stands for in route, 1 stands for out Route.<p>
	 *<dt> currentStop </dt> an integer value which stores the current stop at which the bus arrived or the last stop the bus reached while it is traveling. <p>
	 *<dt> nextStop </dt> an integer value which stores the next stop where the bus is traveling to/set to travel to. <p>
	 *<dt> toNextStop </dt> an integer value which indicates how much time is left until the bus reaches the next stop. <p>
	 *<dt> timeToRest </dt> an integer value which indicates how much time is left until the bus is ready for another trip, the bus is at rest while the valuable is greater than 1 <p>
	 *<dt> busQueue </dt> a list which stores the passenger objects in the bus. 
	 */
	private static int capacity;
	private int route;
	private int currentStop;
	private int nextStop;
	private int toNextStop;
	private int timeToRest;
	private PassengerQueue busQueue;
	
	/**
	 * An empty constructor which creates an empty passenger queue.
	 */
	public Bus(){
		busQueue = new PassengerQueue();
	}
	/**
	 * Receive the capacity of the bus. 
	 * @return capacity as an integer.
	 */
	public int getCapacity(){
		return Bus.capacity;
	}
	/**
	 * Set the capacity of the bus to the specified parameter.
	 * @param capacity the new capacity of the bus.
	 */
	public void setCapacity(int capacity){
		Bus.capacity = capacity;
	}
	/**
	 * Receive the type of the bus.
	 * @return 0 if in route bus, 1 if out route bus.
	 */
	public int getRoute(){
		return this.route;
	}
	/**
	 * Set the route type of the bus to the specified parameter.
	 * @param route the new route of the bus.
	 */
	public void setRoute(int route){
		this.route = route;
	}
	/**
	 * Receive the current stop of the bus.
	 * @return currentStop as an integer.
	 */
	public int getCurrentStop(){
		return this.currentStop;
	}
	/**
	 * Set the current stop of the bus to the specified parameter.
	 * @param currentStop the new current stop of the bus.
	 */
	public void setCurrentStop(int currentStop){
		this.currentStop = currentStop;
	}
	/**
	 * Receive the next stop of the bus.
	 * @return nextStop as an integer.
	 */
	public int getNextStop(){
		return this.nextStop;
	}
	/**
	 * Set the next stop of the bus to the given parameter.
	 * @param nextStop the new next stop of the bus.
	 */
	public void setNextStop(int nextStop){
		this.nextStop = nextStop;
	}
	/**
	 * Receive the remaining time to reach the next stop
	 * @return toNextStop as an integer in minutes.
	 */
	public int getToNextStop(){
		return this.toNextStop;
	}
	/**
	 * Set the remaining time to reach the next stop to the given parameter.
	 * @param toNextStop the new remaining time to reach the next stop.
	 */
	public void setToNextStop(int toNextStop){
		this.toNextStop = toNextStop;
	}
	/**
	 * Receive the remaining time to the next scheduled trip.
	 * @return timeToRest as an integer.
	 */
	public int getTimeToRest(){
		return this.timeToRest;
	}
	/**
	 * Set the remaining time to rest of the bus to the given parameter.
	 * @param timeToRest the next time to rest of the bus.
	 */
	public void setTimeToRest(int timeToRest){
		this.timeToRest = timeToRest;
	}
	/**
	 * Receive the passenger queue of the bus.
	 * @return busQueue as PassengerQueue. The mutator is not provided since modifications will be done directly through the reference.
	 */
	public PassengerQueue getBusQueue(){
		return busQueue;
	}	
}
