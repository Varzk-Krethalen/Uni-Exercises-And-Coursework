# Scheduler assignment

This assignment is about simulating various scheduling strategies. The strategies are
+ **Round Robin**: manage a single list of processes, taking the process at the head of the list
  and allowing it to run for a fixed amount of time (the *quantum*). When a process is complete, 
  remove it from the list.
+ **Priority Scheduling**: threads are created with a priority (*HIGH*, *MED* or *LOW*) and stored
  in a priority queue. Whilst scheduling, take the highest priority process from the queue and allow it to
  run. When a process is complete, remove it from the queue.
+ **Multi-level Feedback Queue**: manage two separate lists of processes, which are to be considered
  *YOUNG* and *OLD*. New processes are added to the *YOUNG* list. Whilst scheduling, if the *YOUNG* lists 
  is not empty, take the process at the head of the list and allow it to run. Then, add it to the *OLD* list.
  If the *YOUNG* list is empty, take a process from the *OLD* list, allow it to run then add it to the *YOUNG*
  list. When both lists are empty, we are done.
  
The `Process` class is a wrapper around a Java `Thread`. There are two ways to create a `Process`. The
first way is to give it a name and specifying the amount of "work" to do (a length of time in milliseconds):

    Process p = new Process("P1", 2000);//A process called p1 that will work for 2 seconds
    
Optionally, we can give the process a priority, which will be ignored by schedulers other than the 
*Priority Scheduler*.

    Process p = new Process("P6", 4000, Process.PRIORITY.HIGH);//A high priority process that will work for 4 seconds

Now that we have a process we can start it running:

    p.start();
    
Every process has a *status* accessed by `p.getStatus()`. The status is either `NEW`, meaning it hasn't started
running yet, `TERMINATED`, meaning that it has finished, or some other value that indicates it is running or
waiting for something. See [Thread.State](https://docs.oracle.com/javase/9/docs/api/java/lang/Thread.State.html)
for more details. If a process is waiting for something we can "wake it up" by calling `p.interrupt()`.

The `Scheduler` class is an abstract class that declares some methods that every subclass must implement,
and includes some code that is common to all schedulers. For each `Scheduler` subclass you need to implement 
the constructor, the `enqueue` method and the `startScheduling` method. A description of what each of them needs 
to do follows.

##Round Robin

The constructor of this scheduler needs to call the constructor of the superclass, passing in the `quantum` 
argument:

    super(quantum);
    
Then you need to initialise the list (such as an `ArrayList`) that will hold the processes. This should be a 
class-level field, so declare the list at the top of the class:

    public class RRScheduler extends Scheduler {
      private ArrayList<Process> queue;
      //...
      
then initialise it inside the constructor by typing `queue = new ArrayList<>()`.

The `enqueue` method simply needs to add processes to the list.

The `startScheduling` method needs to start by creating an empty list which will hold the completed 
processes. This will be the return value of the method. Then, while the queue is not empty, do the 
following:
+ take the next process from the queue and get its State.
+ if the state is NEW, start the process then sleep for QUANTUM milliseconds then put the process at 
  the back of the queue.
+ if the state is TERMINATED, add it to the results list.
+ if the state is anything else then interrupt the process to wake it up then sleep for QUANTUM milliseconds, 
  then put the process at the back of the queue.
+ when the queue is empty, return the list of completed processes.

##Priority Scheduling

The only real difference between this scheduler and the *Round Robin* scheduler is that the processes
each have a priority (*HIGH*, *MED* or *LOW*) and are stored in a priority queue. Import the class
`java.util.PriorityQueue` and declare a class-level field with the type `PriorityQueue<Process>`. 
As before you will initialise this in the constructor. Before doing that, you need to create a `Comparator`
that will determine the order of the queue. This is a function that takes two processes, `p1` and `p2`, 
and returns -1 if the priority of `p1` is less than the priority of `p2`, and 1 if the priority of `p1` is
greater than `p2`. The usual thing to do if the priorities are equal is to return 0, but in this case your
function should return -1. This is so that when a process is added to the queue it ends up behind any other 
processes with the same priority.

The easiest way to define the comparator is by using a lambda expression:

     Comparator<Process> c = (p1, p2) -> {
       //compare the priorities of p1 and p2 and return an int
     };

Then supply the comparator to the constructor of the `PriorityQueue`. 

Other than that, the `startScheduling` method will be the same as for the *Round Robin* scheduler except 
that you should use the `poll` method to get the highest priority item from the queue.

##Multi-level Feedback Queue

This scheduler will manage two lists, which are considered "young" and "old". To schedule the next process 
it does the following:

+ If the the list of young processes is not empty, take the next process, allow it to run then put it at 
  the end of the list of old processes (unless the state of process is TERMINATED, in which case add the
  process to the list of results).
+ If the list of young processes is empty, take the next process from the list of old processes, allow it 
  to run then put it at the end of the list of young processes (as before, unless the state of process is 
  TERMINATED).
                                       
More guidance is given in the source code comments.