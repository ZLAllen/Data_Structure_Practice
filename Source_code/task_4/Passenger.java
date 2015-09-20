package hw_4;
/**
 * A simple class which creates a passenger group object with properties of size, destination and time arrived<p> 
 * Zhaoqi Li<p>
 * 109051837<p>
 * Assignment 4 task 1<p>
 * CSE 214 R05<p>
 * Vyassa Baratham (Recitation TA)<p>
 * Ke Ma (Grading TA)<p>
 */
public class Passenger {
/**
 * Data Field... <p>
 * <dt>size</dt> size of the passenger group 
 * <dt>dest</dt> destination of the passenger group
 * <dt>tArrived</dt> time arrived at the bus stop
 */
	private int size;
	private int dest;
	private int tArrived;
/**
 * An empty constructor which creates an empty passenger object.
 */
	public Passenger(){};
/**
 * A constructor with takes in several parameters and creates a particular passenger object	
 * @param size  size of the passenger group
 * @param destination destination of the passenger group
 * @param timeArrived time arrived at the busStop
 */
	public Passenger(int size, int destination, int timeArrived){
		this.size = size;
		this.dest = destination;
		this.tArrived = timeArrived;	
	}
/**
 * Receive the size of the passenger group 
 * @return size as an integer
 */
	public int getSize(){
		return size;
	}
/**
 * Set the size of the passenger group to the given parameter
 * @param size  the new size of the passenger group
 */
	public void setSize(int size){
		this.size = size;
	}
/**
 * Received the destination of passenger group	
 * @return dest as an integer
 */
	public int getDestination(){
		return dest;
	}
/**
 * Set the destination of the passenger group to the given parameter.
 * @param destination the new destination of the passenger group.
 */
	public void setDestination(int destination){
		this.dest = destination;
	}
/**
 * Received the time when the passenger group arrived at the bus stop.	
 * @return tArrived as an integer.
 */
	public int getTimeArrived(){
		return tArrived;
	}
/**
 * Set the time when the passenger group arrived at the bus stop to the given parameter.	
 * @param timeArrived the new time when the passenger arrived
 */
	public void setTimeArrived(int timeArrived){
		this.tArrived = timeArrived;
	}	
}
