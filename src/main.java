import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class main {
	
	// GLOBAL VARIABLES
	private static int MAXBUFFER; 	// max buffer
    private static int length; 		// current length of the queue
    private static double lambda;   // Arrival rate
    private static double mu;       //Service rate
    private static double rate;		// Rate variable for Negative Exponential Distribution
	private static double time;
	private static double serverBusy;
	private static int dropped;
	private static double prevTime;
	private static double riemannSum;
	
	// negative_exponentially_distributed_time
    public static double negative_exponentially_distributed_time(double rate) {
        double u = Math.random();
        return ((-1/rate) * Math.log(1-u));
    }
	
	// MAIN **************************************************************
	public static void main(String[] args) {
		
		// Initialization
        length = 0; time = 0; dropped = 0; serverBusy = 0; prevTime = 0;
        riemannSum = 0; Event event;
		
		// Set service rate and arrival rate
        mu = 1;
        lambda = rate = 0.1;
		
		// list
		DoublyLinkedList list = new DoublyLinkedList();
		
		// queue
		Queue<Event> queue = new LinkedList<Event>();
		
		// Create the first arrival event and then insert it into the GEL
        Event first = new Event();
        	first.eventTime = time + negative_exponentially_distributed_time(lambda);
        	first.serviceTime = negative_exponentially_distributed_time(mu);
        	first.eventType = 'A';
        
        list.add(first);
        first.print();
        
        // SIMULATION
        for(int i = 0; i < 100000; i++) {
        	
            //Remove the first event from the GEL
            if(!list.isEmpty()) {
                event = list.remove();
                //System.out.println(event);
            }
            else {
                System.err.println("No more elements in GEL to remove");
                break;
            }
            
            
            // 3.3 Processing an Arrival event
            time = event.eventTime;
            
            // riemann sum computation
            if(i == 0)
            {
                prevTime = event.eventTime;
            } else {
                riemannSum = riemannSum + (length * (time - prevTime));
            }
            
            if (event.eventType == 'A') {
                Event nextEvent = new Event();
                nextEvent.eventTime = time + negative_exponentially_distributed_time(lambda);
                nextEvent.serviceTime = negative_exponentially_distributed_time(mu);
                serverBusy = serverBusy + nextEvent.serviceTime;
                nextEvent.eventType = 'A';
            
                // add new packet to GEL in sorted order!
                list.add(nextEvent);
                

                // If the server is free
                if(length == 0) {
                    length++;
                    
                    // Create a departure event at
                    Event departure = new Event();
                    departure.eventTime = event.serviceTime + time;
                    departure.eventType = 'D';
                    
                    //put departure event in GEL
                    list.add(departure);
                }
                // If the server is busy
                else {
                    if(length - 1 < MAXBUFFER) {

                        queue.add(event);
                        length++;
                    } else {
                        //queue is full and the packet will now be dropped
                        dropped++;
                    }
                }
            }	// A
            
            // 3.4 Processing a Departure Event
            else if (event.eventType == 'D') {
            	
                length--;
                
                if(length == 0) {
                    //if the queue is empty we do nothing.
                } 
                
                else {
                    //queue is not full, dequeue the first packet.
                	Event leave = queue.remove();
                    
                    //Create new departure event, instead of making a new node
                    //use the current node;
                    leave.eventTime = time + leave.serviceTime;
                    leave.serviceTime = 0;
                    leave.eventType = 'D';
                    
                    //put new departure event in GEL
                    list.add(leave);
                }
            } 
            
            else {
                System.err.println("event was neither a Departure or Arrival");
            } 
            
            if(i != 0)
            {
                prevTime = event.eventTime;
            }
                
        } // for
        
        
// STATISTICS **************************************************************
        // Utilization:
        System.out.println("\nTime: " + time);
        System.out.println("Server busy: " + serverBusy);
        System.out.println("% utilization of server: %" + (serverBusy/time)*100);
        
        // Mean queue length:
        System.out.println("Fraction of time the server is busy: " + riemannSum/time);
        
        // Number of packets dropped:
        System.out.println("Number of packets dropped: " + dropped);

      

	} // public main void
	
	
} // main

