package hw_2;
/**
 * A simple class which defines an object to store the information of each performance <p> 
 * Zhaoqi Li<p>
 * 109051837<p>
 * Assignment 2 task 1<p>
 * CSE 214 R05<p>
 * Vyassa Baratham (Recitation TA)<p>
 * Ke Ma (Grading TA)<p>
 */
public class PerformanceNode {
/**
 * <dt>Data field:</dt>  Define the name of performance, name of performer in string,<p> 
 * 				the total numbers of performers in integer, the duration and the start time of<p>
 * 				the performance in double.<p>
 * <dt>Precondition:</dt> Duration and start time are in minutes and all number type parameters <p>
 * 				should be non-negative.
 */
	private String performance, performer;
	private int totalPerformers;
	private double duration, startTime;
	private PerformanceNode prev,next;
/**
 * A default constructor which constructs an PerformanceNode object with no data and null links 
 */
	public PerformanceNode(){
		prev = null;
		next = null;
	}
/**
 * A constructor which constructs an PerformanceNode object contains all data and links. <p>
 * <dt>Precondition:</dt> All number types parameters should be non-negative numbers.<p>
 * @param performance Set the name of the performance to the given parameter.
 * @param performer Set the name of the head performer to the given parameter.
 * @param totalPerformers Set the total number of the performers to the given parameter.
 * @param duration Set the duration of the performance to the given parameter.
 * @param prev Set the previous node of the current node to the given node.
 * @param next Set the next node of the current node to the given node.
 */
	public PerformanceNode(String performance, String performer, int totalPerformers, double duration){
		this.performance = performance;
		this.performer = performer;
		this.totalPerformers = totalPerformers;
		this.duration = duration;
		prev = null;
		next = null;
	}
/**
 * Receive the name of the performance.
 * @return performance as a string.
 */
	public String getPerformance(){
		return performance;
	}
/**
 * Set the name of the performance to the given parameter.
 * @param performance the new name of the performance.
 */
	public void setPerformance(String performance){
		this.performance = performance;
	}
/**
 * Receive the name of the head performer.
 * @return performer as a string.
 */
	public String getPerformaner(){
		return performer;
	}
/**
 * Set the name of the head performer to the given parameter.
 * @param performer the new name of the head performer.
 */
	public void setPerformer(String performer){
		this.performer = performer;
	}
/**
 * Receive the number of performers in this PerformanceNode.
 * @return totalPerformers as an integer.
 */
	public int getTotalPerformers(){
		return totalPerformers;
	}
/**
 * Set the number of performers to the given parameter.
 * @param totalPerformers the new number of total performers.
 */
	public void setTotalPerformance(int totalPerformers){
		this.totalPerformers = totalPerformers;
	}
/**
 * Receive the duration of the performance.
 * @return duration as a double.
 */
	public double getDuration(){
		return duration;
	}
/**
 * Set the duration of the performance to the given parameter.
 * <dt>Precondition: </dt> The input parameter should be non-negative double in minute. <p>
 * @param duration the new duration of the performance.
 */
	public void setDuration(double duration){
		this.duration = duration;
	}
/**
 * Receive the start time of the performance.
 * @return startTime as a double.
 */
	public double getStartTime(){
		return startTime;
	}
/**
 * Set the start time of the performance to the given parameter.
 * <dt>Precondition: </dt> The input parameter should be non-negative double in minute. <p>
 * @param startTime the new start time of the performance.
 */
	public void setStartTime(double startTime){
		this.startTime = startTime;
	}
/**
 * Set the next node of the current PerformanceNode to the given parameter.
 * @param node the new node to which the current Node is linked in the following position.
 */
	public void setNext(PerformanceNode node){
		next = node;
	}
/**
 * Set the previous node of the current PerformanceNode to the given parameter.
 * @param node the new node to which the current Node is linked in the previous position.
 */
	public void setPrev(PerformanceNode node){
		prev = node;
	}
/**
 * Receive the next node to which the current PerformanceNode is linked to.
 * @return next as a PerformanceNode.
 */
	public PerformanceNode getNext(){
		return next;
	}
/**
 * Receive the previous node to which the current PerformanceNode is linked to.
 * @return prev as a PerformanceNode.
 */
	public PerformanceNode getPrev(){
		return prev;
	}
/**
 * @return a neatly formatted String containing all the data of this PerformanceNode (ignore references)
 */
	public String toString(){
		return String.format("%-20s%-24s%-18d%-14.2f%-12.2f\n",performance, performer,totalPerformers,
							  duration, startTime);
	}
	/*public static void main(String[] args){
		PerformanceNode c = new PerformanceNode();
		PerformanceNode b = new PerformanceNode();
		PerformanceNode a = new PerformanceNode("a","b",12,20,10,b,c);
		c.setPrev(a);
		b.setNext(a);
		a.getNext().setPerformance("ok?");	//c	
		a.getPrev().setPerformance("seriously?");	//b
		System.out.println(a);
		System.out.println("The name of performance of c is " + c.getPerformance());
		System.out.println("The name of performance of b is " + b.getPerformance());
	}
	*/
}
