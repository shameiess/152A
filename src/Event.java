
public class Event {
	
	/*
	 * Variables.
	 */
	
	// The time when the event occurs.
	public double eventTime;
	
	// True if it is arrival event, False if it is departure.
	public double eventType;
	
	// Point to the next event.
	public Event next;
	
	// Previous event.
	public Event previous;
	
}
