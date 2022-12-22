# CPUScheduling
A java program to simulate the following schedulers:  
  
1. preemptive Shortest- Job First (SJF) Scheduling with context switching
2. Round Robin (RR) with context switching
3. preemptive Priority Scheduling (with the solving of starvation problem)
4. AG Scheduling :
 
    a. Each process is provided a static time to execute called quantum.  
    b. Once a process is executed for given time period, it’s called FCFS till the  
    finishing of (ceil(52%)) of its Quantum time then it’s converted to non-  
    preemptive Priority till the finishing of the next (ceil(52%)), after that it’s  
    converted to preemptive Shortest- Job First (SJF).    
    c. We have 3 scenarios of the running process  
        i. The running process used all its quantum time and it still have job to  
        do (add this process to the end of the queue, then increases its  
        Quantum time by Two).  
        ii. The running process was execute as non-preemptive Priority and  
        didn’t use all its quantum time based on another process converted  
        from ready to running (add this process to the end of the queue, and  
        then increase its Quantum time by ceil(the remaining Quantum  
        time/2) ).  
        iii. The running process was execute as preemptive Shortest- Job First  
        (SJF) and didn’t use all its quantum time based on another process  
        converted from ready to running (add this process to the end of the  
        queue, and then increase its Quantum time by the remaining  
        Quantum time).  
        iv. The running process didn’t use all of its quantum time because it’s no  
        longer need that time and the job was completed (set it’s quantum  
        time to zero).  
          
      
Program Input:    
  
 1. Number of processes  
 2. Round robin Time Quantum  
 3. Context switching  
     
For Each Process you need to receive the following parameters from the user:  
  
 1. Process Name  
 2. Process Arrival Time  
 3. Process Burst Time  
 4. Process Priority 
   
Program Output: </br>
For each scheduler output the following:  
 1. Processes execution order  
 2. Waiting Time for each process  
 3. Turnaround Time for each process  
 4. Average Waiting Time  
 5. Average Turnaround Time  
 6. Print all history update of quantum time for each process (AG Scheduling))  
