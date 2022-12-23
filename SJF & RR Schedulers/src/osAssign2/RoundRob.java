package osAssign2;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;import java.util.Queue;import java.util.LinkedList;
public class RoundRob{
	
	

	    static CPU cpu = new CPU();
	    private static int NumOfP = 0;
	    private static int quantum = 0;
	    static Process[] Processes;
	    static Queue<Process> completed = new LinkedList<Process>();
	    static Queue<Process> ReadyQue = new LinkedList<Process>();
	    static int Context_Switch;

	    void Schedule() {
	        Inputs();
	        OrderFirst();
	        int Time = 0;
	        int Co_Sw = 0;
	        while (completed.size() != NumOfP) {
	            Receive(Time);

	            //context switching
	            if (cpu.Timer == 0 && cpu.CurrentProcess.burstTime > 0 && ReadyQue.size() != 0) {
	                Co_Sw = Context_Switch;
	                System.out.println(Time + " Switching ");
	                while (Co_Sw != 0) {
	                    Co_Sw--;
	                    IncreasingWaitedTime();
	                    Time++;
	                    Receive(Time);
	                }
	            }

	            //completion of a process
	            if (cpu.CurrentProcess.burstTime == 0) {
	                CompletedQue(cpu.CurrentProcess, Time);
	                cpu.Timer = 0;
	            }
	            if (cpu.Timer == 0) {
	                System.out.print(" " + Time);
	                if (cpu.CurrentProcess.burstTime > 0) {
	                    ReadyQue(cpu.CurrentProcess);
	                }
	                if (ReadyQue.size() != 0) {
	                    System.out.print(" " + ReadyQue.peek().processName + " "); //returns head without remove
	                    cpu.Timer = quantum;
	                    cpu.CurrentP(ReadyQue.poll()); //returns head and remove
	                } else {
	                    cpu.CurrentProcess = new Process();
	                    Time++;
	                    continue;
	                }
	            }
	            cpu.CurrentProcess.burstTime--;
	            cpu.Timer--;

	            IncreasingWaitedTime();
	            Time++;
	        }
	        System.out.println("");
	        ShowDetails();
	    }

	    static void ShowDetails() {
	    	System.out.println("Pname   "+" WT    "+" TAT   ");
	        for (Process p : completed) {
	            System.out.print(p.processName + "\t" + p.waitingTime );
	            System.out.println("\t" + p.turnAroundTime+"  ");
	        }
	        System.out.print("Average Waiting Time: ");
	        System.out.println(" =" + AverageWaitingT());
	        System.out.print("Average Turnaround Time: ");
	        System.out.println(" =" + AverageTurnaround());
	    }

	    static float AverageTurnaround() {
	        float sum = 0;
	        for (Process p : completed) {
	            sum += p.turnAroundTime;
	        }
	        return (float) sum / NumOfP;
	    }

	    static void Receive(int arrival_T) {
	        for (int i = 0; i < NumOfP; i++) {
	            if (Processes[i].arrivalTime == arrival_T) {
	                ReadyQue(Processes[i]);
	            }
	        }
	    }

	    static float AverageWaitingT() {
	        float sum = 0;
	        for (Process p : completed) {
	            sum += p.waitingTime;
	        }
	        return (float) sum / NumOfP;
	    }

	    static void CompletedQue(Process p, int T) {
	        completed.offer(p);
	        p.completionTime = T;
	        p.setTurnaround();
	    }

	    static void IncreasingWaitedTime() {
	        int n = ReadyQue.size();
	        for (Process p : ReadyQue) {
	            p.waitingTime++;
	        }
	    }

	    static void ReadyQue(Process p) {
	        ReadyQue.offer(p);
	    }

	    private static void OrderFirst() {
	        for (int i = 0; i < NumOfP - 1; i++) {
	            for (int j = 0; j < NumOfP - 1; j++) {
	                if (Processes[j].arrivalTime > Processes[j + 1].arrivalTime)
	                    Swap(j, j + 1);
	            }
	        }
	    }

	    static void Swap(int i, int j) {
	        Process temp;
	        temp = Processes[i];
	        Processes[i] = Processes[j];
	        Processes[j] = temp;
	    }

	    static void PrintProcesses(Process[] p) {
	        int n = p.length;
	        for (int i = 0; i < n; i++) {

	            System.out.println(p[i].processName + " ");
	        }
	    }

	    static void Initialization() {
	        Processes = new Process[NumOfP];
	        for (int i = 0; i < NumOfP; i++) {
	            Processes[i] = new Process();
	        }
	    }

	    static void Inputs() {
	        Scanner scan = new Scanner(System.in);
	        System.out.println("Enter number of Processes: ");
	        NumOfP = scan.nextInt();
	        System.out.println("Quantum Time: ");
	        quantum = scan.nextInt();
	        System.out.println("Context Switching: ");
	        Context_Switch = scan.nextInt();
	        Initialization();
	        scan.nextLine();
	        System.out.println("Enter processes names: ");
	        for (int i = 0; i < NumOfP; i++) {
	            Processes[i].processName = scan.next();
	        }
	        System.out.println("Enter processes Arrival times: ");
	        for (int i = 0; i < NumOfP; i++) {
	            Processes[i].arrivalTime = scan.nextInt();
	        }
	        System.out.println("Enter processes Burst times: ");
	        for (int i = 0; i < NumOfP; i++) {
	            Processes[i].burstTime = scan.nextInt();
	        }
	    }
	
}
	
	       
	
/*
 * 
	int n,i,arrival ,Burst, quant,count=0,context,temp,sequence=0,burst[],wt[],tat[],remaining_burst[],comp[] ;  
	float avgwaiting=0,avgtat=0;
	String Name;
	ArrayList<Process> list = new ArrayList<Process>();
	Scanner s=new Scanner(System.in);  
	Scanner nameSc = new Scanner(System.in);
	public void Schedule() {
		System.out.print("Enter the number of process ,context switch time: ");  
		n = s.nextInt();  
		context = s.nextInt();
		burst = new int[n];  
		wt = new int[n];  
		tat = new int[n];
		comp = new int[n];
		remaining_burst = new int[n];
		System.out.print("Enter the name ,arrivaltime , burst time of the process\n");  
		for (i=0;i<n;i++)  
		{
			System.out.print("P"+(i+1)+" = "); 
			Process P = new Process();
			Name = nameSc.nextLine();
			arrival = s.nextInt();
			Burst = s.nextInt();
			P.setAT(arrival);
			P.setBurstTime(Burst);
			P.setRT(Burst);
			P.setName(Name);
			P.setCT(0);
			list.add(P);
			burst[i] = list.get(i).getBurstTime();
			//burst[i] = s.nextInt();  
			remaining_burst[i] = burst[i];  
			}  
		System.out.print("Enter the quantum time: ");  
		quant = s.nextInt();
		while(true)  
		{  
		for (i=0,count=0;i<n;i++)  
		{  
		temp = quant;  
		if(remaining_burst[i] == 0)  // process has finished 
		{  
		count++;  
		continue;  
		}  
		if(remaining_burst[i]>quant && sequence <= list.get(i).arrivalTime) {  
			remaining_burst[i]= remaining_burst[i] - quant;  
			list.get(i).remainingTime = remaining_burst[i];
			}
		
		else if(remaining_burst[i]>=0) // less than quantum   
		{  
			temp = remaining_burst[i];  
			remaining_burst[i] = 0;
			list.get(i).remainingTime=0;
		}  
		sequence = sequence + temp;  
		tat[i] = sequence;  
		sequence+=context;
		}  
		if(n == count)  
		break;  
		}  
		System.out.print("--------------------------------------------------------------------------------");  
		System.out.print("\nP      BurstTime     TurnaroundTime       CompT         WaitingTime\n");  
		System.out.print("--------------------------------------------------------------------------------");  
		for(i=0;i<n;i++)  
		{  
		wt[i]=tat[i]-burst[i];  
		comp[i]=tat[i]- list.get(i).arrivalTime;
		avgwaiting=avgwaiting+wt[i];  
		avgtat=avgtat+tat[i];  
		System.out.print("\n "+(i+1)+"\t "+burst[i]+"\t\t "+tat[i]+"\t\t "+comp[i]+"\t\t "+wt[i]+"\n");  
		}  
		avgwaiting=avgwaiting/n;  
		avgtat=avgtat/n;  
		System.out.println("\nAverage waiting Time = "+avgwaiting+"\n");  
		System.out.println("Average turnaround time = "+avgtat);   
		//8 2 7 3 5
		//32 6 34 14 29 
		//24 4 27 11 24
		
		
	}}*/
		
			
	


