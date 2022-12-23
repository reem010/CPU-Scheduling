package osAssign2;
public class Process {
	String processName;
	int arrivalTime;
	int burstTime;
	int waitingTime;
	int turnAroundTime;
	int completionTime;
	int remainingTime; 
	int Priority; 		
	int quantum;  		

	public Process(){}
	public Process(String name, int arrival, int burst, int quantum, int priority ){	
		this.processName = name;
		this.arrivalTime = arrival;
		this.burstTime = burst;
		this.Priority = priority;
		this.quantum = quantum;
		this.waitingTime = -1;
		this.remainingTime = -1;
		this.turnAroundTime = -1;	
	}
	
	public String getName() {
		return processName;
		}
	public void setName(String Name) {
		this.processName = Name;
		}
	
	public int getAT() {
		return arrivalTime;
		}
	public void setAT(int AT) {
		this.arrivalTime = AT;
		}
	
	public int getBurstTime() {
		return burstTime;
		}
	public void setBurstTime(int BTime) {
		this.burstTime = BTime;
		}
	
	public int getWT() {
		return waitingTime;
		}
	public void setWT(int WT) {
		this.waitingTime = WT;
	}
	
	public int getTAT() {
		return turnAroundTime;
		}
	public void setTAT(int TAT) {
		this.turnAroundTime = TAT;
		}
	
	public int getCT() {
		return completionTime;
		}
	public void setCT(int CT) {
		this.completionTime = CT;
	}
	public void setTurnaround(){
		turnAroundTime = completionTime -arrivalTime;
    }
	public int getRT() {
		return remainingTime;
		}
	public void setRT(int RemainingTime) {
		this.remainingTime = RemainingTime;
		}
	
	public int getPriority() {
		return Priority;
		}
	public void setPriority(int Priority) {this.Priority = Priority;}
	
	public int getmQ() {
		return quantum;
		}
	public void setQ(int Quantum) {
		this.quantum = Quantum;
		}

}
