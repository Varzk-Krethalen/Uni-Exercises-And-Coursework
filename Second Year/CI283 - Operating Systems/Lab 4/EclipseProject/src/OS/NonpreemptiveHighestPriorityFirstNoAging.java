package OS;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/*******************************************************************
 * Extends Scheduler as a Non-preemptive highest priority first algorithm w/o aging
 * which schedules based on priority and allows process to finish even if a higher
 * priority process has become ready to run
 * Reads a PriorityQueue<Process>, schedules it, and returns a new Queue<Process>
 * *****************************************************************/

public class NonpreemptiveHighestPriorityFirstNoAging extends Scheduler {    
  @SuppressWarnings("rawtypes")
  @Override
  public Queue<Process> schedule(PriorityQueue<Process> q) {
    int finishTime = 0;
    int startTime;
    Process p;
    Process scheduled;
    Scheduler.Stats stats = this.getStats();
    Queue<Process> scheduledQueue = new LinkedList<>();
        
    // Queue processes that are ready to run, and order by shortest run time 
    // break ties with arrival time so they are readied in the correct order
    @SuppressWarnings("unchecked")
	PriorityQueue<Process> readyQueue = new PriorityQueue<>(10, 
      new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
          Process p1 = (Process) o1;
          Process p2 = (Process) o2;
          if (p1.getPriority() == p2.getPriority())
            return p1.getArrivalTime() < p2.getArrivalTime() ? -1 : 1;
          else
            return p1.getPriority() < p2.getPriority() ? -1 : 1;
        }            
      });
        
    while (!q.isEmpty() || !readyQueue.isEmpty()) {
      
    	// add processes that have arrived by now to the ready queue
      while (!q.isEmpty() && q.peek().getArrivalTime() <= finishTime)
        readyQueue.add(q.poll());
            
      p = readyQueue.isEmpty() ? q.poll() : readyQueue.poll();
            
      startTime = Math.max((int) Math.ceil(p.getArrivalTime()), finishTime);            
      finishTime = startTime + p.getBurstTime();
            
      // Don't start any processes after 100 time slices
      if (startTime > 100) break;
            
      // Record the statistics for this process
      stats.addWaitTime(startTime - p.getArrivalTime());
      stats.addTurnaroundTime(finishTime - p.getArrivalTime());
      stats.addResponseTime(finishTime - startTime);
      stats.addProcess();                     

      // Create a new process with the calculated start time and add to a new queue
      scheduled = new Process();
      scheduled.setBurstTime(p.getBurstTime());
      scheduled.setStartTime(startTime);
      scheduled.setName(p.getName());
      scheduledQueue.add(scheduled);              
    }        
    stats.addQuanta(finishTime); // Add the total quanta to finish all jobs
    printTimeChart(scheduledQueue);
    printRoundAvgStats();
    stats.nextRound();
        
    return scheduledQueue;
  }

public static void main(String[] args) throws CloneNotSupportedException { 
    int SIMULATION_RUNS = 3;
    int MAX_PROCESSES_PER_RUN = 10;
    int ALGORITHM_COUNT = 1;

    // Create a scheduler for the scheduling algorithm
    Scheduler prfcfs = new PreemptiveFirstComeFirstServedNoAging();

    // Hold duplicated process queues for each algorithm to use
    @SuppressWarnings("unchecked")
	PriorityQueue<Process>[] q  = new PriorityQueue[ALGORITHM_COUNT + 1];
   // q = (PriorityQueue<Process>[]) q;
    
    // Test the scheduling algorithm SIMULATION_RUNS times
    for (int i = 0; i < SIMULATION_RUNS; ++i) {
      System.out.println("---------------------------");
      System.out.format("Scheduling Process Queue %d:\n", i + 1);
      System.out.println("---------------------------");
            
      //generate a new process queue for this testing round then duplicate it
      q[0] = ProcessFactory.generateProcesses(MAX_PROCESSES_PER_RUN);
      for (int j = 1; j < ALGORITHM_COUNT + 1; ++j)
          q[j] = copyQueue(q[0]);
     
                  
      // Print the process list by ascending arrival time   
      while (!q[ALGORITHM_COUNT].isEmpty())
          System.out.println(q[ALGORITHM_COUNT].poll());
                        
      // Run the scheduling algorithm and show the results           
      System.out.println("\n---------------------------");
      System.out.println("\nPreemptive First Come First Served");
      prfcfs.schedule(q[0]);                                            
    }
        
    System.out.println("\n-------------------------------------------");
    System.out.println("Average Statistics by Scheduling Algorithm");

    System.out.println("\n---------------------------");
    System.out.println("\nPreemptive First Come First Served");
    prfcfs.printAvgStats();
  }

private static PriorityQueue<Process> copyQueue(PriorityQueue<Process> q) throws CloneNotSupportedException {        
    PriorityQueue<Process> qcopy = new PriorityQueue<>();
    ArrayList<Process> qoriginal = new ArrayList<>();
    while (!q.isEmpty()) {
      Process p = q.poll();
      qcopy.add((Process) p.clone());
      qoriginal.add(p);
    }
    q.addAll(qoriginal);
    return qcopy;
  }
}
