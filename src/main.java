import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	// GLOBAL VARIABLES
	private static int MAXBUFFER; 	// max buffer
    private static int length; 		// current length of the queue
    private static double lambda;   // Arrival rate
    private static double mu;       //Service rate
    private static double rate;		// Rate variable for Negative Exponential Distribution
	private static double time;
	
	
	// negative_exponentially_distributed_time
    public static double negative_exponentially_distributed_time(double rate) {
        double u = Math.random();
        return ((-1/rate) * Math.log(1-u));
    }
	
	// MAIN **************************************************************
	public static void main(String[] args) {
		
		time = 0;
        mu = 1;
        lambda = rate = 0.1;
		
		// list
		DoublyLinkedList list = new DoublyLinkedList();
		
		// queue
		Queue<Event> queue = new LinkedList<Event>();
		
		//
        Event first = new Event();
        	first.eventTime = time + negative_exponentially_distributed_time(lambda);
        	first.serviceTime = negative_exponentially_distributed_time(mu);
        	first.eventType = 'A';
        
        list.add(first);
        first.print();
        
        // PRELIMINARY shouldn't actually work yet.
        for (int i = 0; i < 100000; i++)
        {
        	// Creates an Event object.
        	Event ev;
        	
        	// Gets the first event from the double linked list.
        	ev = list.remove();
        	
        	// IF event is an arrival.
        	if ('A' == ev.eventType){
        		
        	} //if
        	else{
        		
        	} //else
        }
		
	}
}
