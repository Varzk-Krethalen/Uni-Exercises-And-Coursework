import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Extends Scheduler as a First Come First Served algorithm
 * Reads a PriorityQueue<Process>, schedules it, and returns a new Queue<Process>
 */
public class FirstComeFirstServed extends Scheduler {
	
  @Override
  public Queue<Process> schedule(PriorityQueue<Process> q) {
    int startTime = 0; // the current time slice
    int queueSize = q.size();
    int finishTime = 0;
    Process p;
    Process scheduled;
    Stats stats = this.getStats();
    Queue<Process> scheduledQueue = new LinkedList<>();
        
    for (int i = 0; i < queueSize; ++i) {
      p = q.poll(); // MUST BE POLLED SEPARATELY for PriorityQueue to update
                    // for (Process p : q) will give the wrong order!
            
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

    // Create a scheduler for each scheduling algorithm
    Scheduler fcfs = new FirstComeFirstServed();

        // Hold duplicated process queues for each algorithm to use
        @SuppressWarnings("unchecked")
		PriorityQueue<Process>[] q = new PriorityQueue[ALGORITHM_COUNT + 1];
        q = (PriorityQueue<Process>[]) q;
        
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
                        
            // Run each scheduling algorithm and show the results
            
            System.out.println("\n---------------------------");
            System.out.println("\nFirst Come First Served");
            fcfs.schedule(q[0]);                                            
        }
        
        System.out.println("\n-------------------------------------------");
        System.out.println("Average Statistics by Scheduling Algorithm");

        System.out.println("\n---------------------------");
        System.out.println("\nFirst Come First Served");
        fcfs.printAvgStats();
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
