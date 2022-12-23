import java.util.*;


public class Main {
	public static void main(String[] args) {
			int n;
			Scanner sc=new Scanner(System.in);
			n=sc.nextInt();
			Process []arr = new Process[n];
			for(int i=0;i<n;i++){
				System.out.println("enter a Process info(name,burst time, arrival time ,priority,qunatum): ");
				String a=sc.next();
				int b=sc.nextInt();
				int c=sc.nextInt();
				int d=sc.nextInt();
				int e=sc.nextInt();
				arr[i]=new Process(a,b,c,d,e);
				
			}
			// sorting the array
			 for (int i = 1; i < n; ++i) {
		            Process key = arr[i];
		            int j = i - 1;
		            while (j >= 0 && arr[j].arrivalTime > key.arrivalTime) {
		                arr[j + 1] = arr[j];
		                j = j - 1;
		            }
		            arr[j + 1] = key;
		    }
			 AgScheduler obj=new AgScheduler(arr,n);
			 obj.start();
	}

}
