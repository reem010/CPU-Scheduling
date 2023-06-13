# CPUScheduling
<html>
<body>
  <p>
    This Java program simulates different schedulers. It provides simulations for the following schedulers:
  </p>
  <ol>
    <li>
      Preemptive Shortest-Job First (SJF) Scheduling with context switching.
    </li>
    <li>
      Round Robin (RR) with context switching.
    </li>
    <li>
      Preemptive Priority Scheduling with the solution to the starvation problem.
    </li>
    <li>
      AG Scheduling:
      <ul>
        <li>
          Each process is provided with a static time to execute called quantum.
        </li>
        <li>
          Once a process is executed for the given time period, it is treated as FCFS (First-Come, First-Served) until it finishes ceil(52%) of its quantum time. Then it is converted to non-preemptive Priority until it finishes the next ceil(52%) of its quantum time. After that, it is converted to preemptive Shortest-Job First (SJF).
        </li>
        <li>
          There are 3 scenarios for the running process:
          <ol type="i">
            <li>
              The running process used all its quantum time and still has more job to do. In this case, the process is added to the end of the queue, and its quantum time is increased by two.
            </li>
            <li>
              The running process was executed as non-preemptive Priority and did not use all its quantum time because another process was converted from ready to running. In this case, the process is added to the end of the queue, and its quantum time is increased by ceil(the remaining quantum time/2).
            </li>
            <li>
              The running process was executed as preemptive Shortest-Job First (SJF) and did not use all its quantum time because another process was converted from ready to running. In this case, the process is added to the end of the queue, and its quantum time is increased by the remaining quantum time.
            </li>
            <li>
              The running process did not use all of its quantum time because it no longer needs that time and the job was completed. In this case, the process's quantum time is set to zero.
            </li>
          </ol>
        </li>
      </ul>
    </li>
  </ol>

  <h2>Program Input</h2>
  <p>The program expects the following input:</p>
  <ol>
    <li>Number of processes</li>
    <li>Round robin time quantum</li>
    <li>Context switching</li>
  </ol>
  <p>For each process, the following parameters are required:</p>
  <ol>
    <li>Process Name</li>
    <li>Process Arrival Time</li>
    <li>Process Burst Time</li>
    <li>Process Priority</li>
  </ol>

  <h2>Program Output</h2>
  <p>For each scheduler, the program outputs the following:</p>
  <ol>
    <li>Processes execution order</li>
    <li>Waiting time for each process</li>
    <li>Turnaround time for each process</li>
    <li>Average waiting time</li>
    <li>Average turnaround time</li>
    <li>All history updates of quantum time for each process (AG Scheduling)</li>
  </ol>

</body>
</html>
