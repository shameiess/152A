
public class Event {
	
	public double eventTime;
	public double serviceTime;
	public double eventTime_arrival;
	public double eventTime_departure;
	public char eventType;
	public Event next;
	public Event previous;
	
	// Default Constructor
    public Event() {
    	eventTime = 0;
    	serviceTime = 0;
        eventTime_arrival = 0;
        eventTime_departure = 0;
        eventType = 'X';
    }
    
    // Constructor.
    public Event(double time, double service, char type) {
    	eventTime = time;
    	serviceTime = service;
    	eventType = type;
    }

    // print the event
    public void print() {
        System.out.print("eventTime is: " + this.eventTime + ". serviceTime is " + this.serviceTime + " For event: " + this.eventType
        );
    }


}
