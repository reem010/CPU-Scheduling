import java.util.*;
public class AgScheduler {
	public  int curInp=0;
	public int n;
	public  int t; // time 
	Process []arr; // input array
	
	AgScheduler(Process []_arr,int _n){ // take input array and size of process
		n=_n;
		arr=new Process[_n];
		for(int i=0;i<n;i++){
			arr[i]=new Process(_arr[i].name,_arr[i].burstTime,_arr[i].arrivalTime,_arr[i].priority,_arr[i].q);
		}
	}
	
	public int checkp(ArrayList<Process> qu,int curr){
		int indx=-1;
		int i=0;
		for(Process el:qu){
			if(el.priority< curr){
				indx=i;
				curr=el.priority;
			}
			i++;
		}
		return indx;
	}
	public int checks(ArrayList<Process> qu,int curr){
		int indx=-1;
		int i=0;
		for(Process el:qu){
			if(el.tempBurstTime<curr){
				indx=i;
				curr=el.tempBurstTime;
			}
			i++;
		}
		return indx;
	}

	void is(Process[] arr,ArrayList<Process> qu){ // check if any process has come
		while(true){
			if(curInp>=n)break;
			if(arr[curInp].arrivalTime>t)break;
			qu.add(arr[curInp++]);
		}
	}
	
	void start(){
		ArrayList<String>list=new ArrayList<String>(); // output list
		t=0;
		curInp=0;
		int deads=0;
		Process cur=null;
		ArrayList<Process> qu = new ArrayList<Process>(); // waiting queue
		
		while(deads<n){
			is(arr,qu);
			if(qu.isEmpty()&&cur==null){t++;continue;}
			
			if(cur==null){
				cur=qu.get(0);
				qu.remove(0);
			}
			
			// work first 25% 
			int doneq=(int) Math.ceil((double)cur.q *1/4);
			t+=Math.min(doneq,cur.tempBurstTime);
			cur.tempBurstTime-=doneq;
			
			// dead check
			if(cur.tempBurstTime<=0){
				// print quantum
				System.out.println("process quantum:");
				for(int j=0;j<n;j++){
					if(arr[j]==cur)
						cur.q=0;
					System.out.println(arr[j].name+"  "+arr[j].q);
				}
				deads++; cur.terminateTime=t;
				list.add(cur.name+" time= "+String.valueOf(t));
				cur=null;continue;
			}
			
			is(arr,qu);
			
			// check priority 
			int tmp=checkp(qu,cur.priority);
			if(tmp!=-1){				
				int rem=(int) Math.ceil((double)(cur.q-doneq)/(double)2);
				
				// print quantum
				System.out.println("process quantum:");
				for(int j=0;j<n;j++){
					System.out.print(arr[j].name+"  "+arr[j].q);
					if(arr[j]==cur)
						System.out.print("+"+rem);
					System.out.println("");
						
				}

				cur.q+=rem;
				
				qu.add(cur);
				list.add(cur.name+" time= "+String.valueOf(t));
				
				cur=qu.remove(tmp);
				
				continue;
			}
			
			
			// work till 50%
			int dh=(int) Math.ceil((double)cur.q *1/2);
			t+=Math.min((dh-doneq),cur.tempBurstTime);
			cur.tempBurstTime-=(dh-doneq);
			
			// dead check
			if(cur.tempBurstTime<=0){
				// print quantum
				System.out.println("process quantum:");
				for(int j=0;j<n;j++){
					if(arr[j]==cur)
						cur.q=0;
					System.out.println(arr[j].name+"  "+arr[j].q);
				}
				deads++; cur.terminateTime=t;list.add(cur.name+" time= "+String.valueOf(t));;cur=null;continue;
			}
			
			
			
			// work till the end of the quantum time but preemptive
			boolean fail=false;
			int workTime=Math.min(cur.q-dh,cur.tempBurstTime);
			if(workTime==0){// dead check
				list.add(cur.name+" time= "+String.valueOf(t));
				if(cur.tempBurstTime<=0){deads++; cur.terminateTime=t;}
				else {cur.q=2;cur.quArrivalTime=t;qu.add(cur);}
				cur=null;
				continue;
			}
	
			while(workTime>0){
				is(arr,qu);
				// check Shortest job
				tmp=checks(qu,cur.tempBurstTime);
				if(tmp!=-1){
					// print quantum
					System.out.println("process quantum:");
					for(int j=0;j<n;j++){
						System.out.print(arr[j].name+"  "+arr[j].q);
						if(arr[j]==cur)
							System.out.print("+"+(cur.q-dh));
						System.out.println("");
					}
					
					cur.q+=(cur.q-dh);
					list.add(cur.name+" time= "+String.valueOf(t));
					qu.add(cur);
					
					fail=true;
					cur=qu.remove(tmp);
					break;
				}
				workTime--;
				cur.tempBurstTime--;
				t++;
			}			
			if(fail==true)continue;
			
			list.add(cur.name+" time= "+String.valueOf(t));
			
			// dead check
			if(cur.tempBurstTime<=0){
				// print quantum
				System.out.println("process quantum:");
				for(int j=0;j<n;j++){
					if(arr[j]==cur)
						cur.q=0;
					System.out.println(arr[j].name+"  "+arr[j].q);
				}
				deads++; cur.terminateTime=t;list.add(cur.name+" time= "+String.valueOf(t));cur=null;continue;
			}
			else {
				// print quantum
				System.out.println("process quantum:");
				for(int j=0;j<n;j++){
					if(arr[j]==cur)
						System.out.println(arr[j].name+"  "+"0+2");
					else
						System.out.print(arr[j].name+"  "+arr[j].q);
				}
				
				cur.q=2;cur.quArrivalTime=t;qu.add(cur);
			}
			cur=null;
		}
		
		System.out.println("Processes execution order:");
		for(String h:list)System.out.println(h);
		
		System.out.println("waiting time for each process:");
		// print waiting time
		int avgW=0;
		for(int i=0;i<n;i++){
			System.out.println(arr[i].name+" "+ (arr[i].terminateTime-arr[i].arrivalTime-arr[i].burstTime));
			avgW+=(arr[i].terminateTime-arr[i].arrivalTime-arr[i].burstTime);
		}
		
		System.out.println("Turnaround time for each process:");
		int avgT=0;
		for(int i=0;i<n;i++){
			System.out.println(arr[i].name+" "+(arr[i].terminateTime-arr[i].arrivalTime));
			avgT+=(arr[i].terminateTime-arr[i].arrivalTime);
		}
		System.out.println("average waiting time : "+(float)avgW/n);
		System.out.println("Turnaround time : "+(float)avgT/n);
		
	}
}
