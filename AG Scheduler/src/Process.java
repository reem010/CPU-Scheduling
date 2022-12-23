
public class Process {
	public String name;
	public int arrivalTime;
	public int totalWaiting=0;
	public int quArrivalTime;
	public int tempBurstTime;
	public int burstTime;
	public int priority;
	public int terminateTime;
	public int totalWait=0;
	public int q;
	Process(String _name,int _burstTime,int _arrivalTime,int _priority,int _q){
		name=_name;
		arrivalTime=_arrivalTime;
		tempBurstTime=_burstTime;
		burstTime=_burstTime;
		priority=_priority;
		quArrivalTime=_arrivalTime;
		this.q=_q;
	}
	
}
