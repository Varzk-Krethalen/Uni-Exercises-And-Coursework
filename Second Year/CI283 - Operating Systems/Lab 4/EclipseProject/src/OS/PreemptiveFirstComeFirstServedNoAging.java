package OS;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/*******************************************************************
 * Extends Scheduler with a FCFS algorithm w/o aging which uses preemptive scheduling 
 * with priority first then arrival time as a tiebreaker
 * Reads a PriorityQueue<Process>, schedules it, and returns a new Queue<Process>
 * *****************************************************************/

public class PreemptiveFirstComeFirstServedNoAging extends Scheduler {    
@Override
    
  public Queue<Process> schedule(PriorityQueue<Process> q) {
    int finishTime = 0;
    int startTime;
    Process p;
    Process scheduled;
    Process remaining;
    Scheduler.Stats stats = this.getStats();
    Queue<Process> scheduledQueue = new LinkedList<>();
        
    // Need to keep track of these to calculate turnaround and wait times
    Map<Character, Integer> startTimes = new HashMap<>();
    Map<Character, Integer> finishTimes = new HashMap<>();
     
    // Queue processes that are ready to run, and order by shortest run time
    // break ties with arrival time so they are readied in the correct order
    @SuppressWarnings({ "unchecked", "rawtypes" })
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
        
    // Queue processes that are waiting to run by priority and arrival time
    PriorityQueue<Process> waitingQueue = new PriorityQueue<>(10,    
      new Comparator<Object>() {
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
        
    while (!q.isEmpty() || !readyQueue.isEmpty() || !waitingQueue.isEmpty()) {
      
    	// add processes that have arrived by now to the ready queue
        while (!q.isEmpty() && q.peek().getArrivalTime() <= finishTime) readyQueue.add(q.poll());
            
        // Get the process with the shortest remaining time that can start now
        // Break ties Waiting > Ready > Q to prioritize already running process 
        if (readyQueue.isEmpty())
          p = (waitingQueue.isEmpty()) ? q.poll() : waitingQueue.poll();
          else if (waitingQueue.isEmpty())
            p = readyQueue.poll();
          else                         // needs to be < not <= to prioritize waitingQueue
            p = (readyQueue.peek().getPriority() < waitingQueue.peek().getPriority()) ? readyQueue.poll() : waitingQueue.poll();
            
        // Assign p one time slice for now
        startTime = Math.max((int) Math.ceil(p.getArrivalTime()), finishTime);
        finishTime = startTime + 1;
            
        // Record some stats if we haven't seen this process before
        if (!startTimes.containsKey(p.getName())) {
          if (startTime > 100) break;
          startTimes.put(p.getName(), startTime);
          stats.addWaitTime(startTime - p.getArrivalTime());
          stats.addResponseTime(startTime - p.getArrivalTime() + 1);
        }
        else // add the wait time this process was in waitingQueue
          stats.addWaitTime(startTime - finishTimes.get(p.getName()));
            
        // split p into a second process with n-1 time slices and add to waiting queue
        if (p.getBurstTime() > 1) {
          try {
            remaining = (Process) p.clone();
            remaining.setBurstTime(remaining.getBurstTime() - 1);
            waitingQueue.add(remaining);
            finishTimes.put(remaining.getName(), finishTime);
            } 
            catch (CloneNotSupportedException ex) {
              Logger.getLogger(NonpreemptiveHighestPriorityFirstNoAging.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
        else { // this process finished so record turnaround time
          stats.addTurnaroundTime(finishTime - startTimes.get(p.getName()));
          stats.addProcess();
        }  
        
        // Create a new process with the calculated start time and add to a new queue
        scheduled = new Process();
        scheduled.setBurstTime(1);
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
}