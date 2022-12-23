package osAssign2;
import java.util.ArrayList;
import java.util.Scanner;
public class PreemativeSJF {
	
		String ProcessName;
		int numProcesses;
		int arrivalTime;
		int burstTime;
		int contextswitchTime, overheadCounter = 0;
		int min, i = 0, total = 0, time = 0, c;
		float avgwaitingtime = 0, avgTAT = 0;
		ArrayList<Process> list = new ArrayList<Process>();
		ArrayList<Process> last = new ArrayList<Process>();
		ArrayList<Process> processNames = new ArrayList<Process>();
		ArrayList<Process> output = new ArrayList<Process>();
		ArrayList<Integer> timeline = new ArrayList<Integer>();
		Scanner sc = new Scanner(System.in);
		Scanner nameSc = new Scanner(System.in);
		public void Schedule() {
			System.out.println("Enter number of processes ,context switch time:");
			numProcesses = sc.nextInt();
			contextswitchTime = sc.nextInt();
			for (int i = 0; i < numProcesses; i++) {
				Process P = new Process();
				System.out.println("Enter process name in separate line, arrival time, burst time:");
				ProcessName = nameSc.nextLine();
				arrivalTime = sc.nextInt();
				burstTime = sc.nextInt();
				P.setAT(arrivalTime);
				P.setBurstTime(burstTime);
				P.setRT(burstTime);
				P.setName(ProcessName);
				P.setCT(0);

				processNames.add(P);
				list.add(P);
			}

			while (true) {
				min = 100;
				c = numProcesses;
				// if finished
				if (total == numProcesses) {
					System.out.println("Saving data of " + last.get(i - 1).getName() + " in PCB");
					timeline.add(time);
					//timeline.add(last.get(i-1).getmCompletionTime());
					break;
				}
				// Find process with minimum burst time
				for (int j = 0; j < list.size(); j++) {
					if ((list.get(j).getAT() <= time) && (list.get(j).getRT() > 0)&& (list.get(j).getRT() < min)) {
						min = list.get(j).getRT();
						last.add(i, list.get(j));
						c = j;
					}
				}
				// if no processes arrived yet
				if (c == numProcesses) {
					time++;
				} else {
					if (i == 0) {
						list.get(c).setRT((list.get(c).getRT() - 1));
						System.out.println("Loading data of " + list.get(c).getName() + " from PCB");
						// list.get(c).setmCompletionTime(list.get(c).getmCompletionTime() +
						// overheadTime);
						System.out.println(list.get(c).getName() + " is executing");
						output.add(list.get(c));
						timeline.add(time);
						time += (contextswitchTime + 1);
						i++;
					} else if ((last.get(i - 1).getName().equals(list.get(c).getName()))) {
						list.get(c).setRT((list.get(c).getRT() - 1));
						time++;
						i++;

					} else {
						list.get(c).setRT((list.get(c).getRT() - 1));
						overheadCounter += (contextswitchTime * 2);
						//System.out.println("Saving data of " + last.get(i - 1).getName() + " in PCB");
						//System.out.println("Loading data of " + list.get(c).getName() + " from PCB");
						//System.out.println(list.get(c).getName() + " is executing");
						output.add(list.get(c));
						timeline.add(time);
						timeline.add(time);
						time += ((contextswitchTime * 2) + 1);
						i++;
					}
					if (list.get(c).getRT() == 0) {
						list.get(c).setCT(list.get(c).getCT() + time);
						total++;
					}
				}

			}

			for (int y = 0; y < list.size(); y++) {
				list.get(y).setTAT(list.get(y).getCT() - list.get(y).getAT());
				list.get(y).setWT(list.get(y).getTAT() - list.get(y).getBurstTime());
				avgwaitingtime += list.get(y).getWT();
				avgTAT += list.get(y).getTAT();
			}

			System.out.println("P       arr      bur      comp       tat        wait");

			for (int y = 0; y < list.size(); y++) {
				System.out.println(list.get(y).getName() + "       " + list.get(y).getAT() + "       "
						+ list.get(y).getBurstTime() + "        " + list.get(y).getCT() + "           "
						+ list.get(y).getTAT() + "          " + list.get(y).getWT());
			}

			System.out.println("average wait time = " + avgwaitingtime/numProcesses+ "  average turn around time = "+ avgTAT / numProcesses);

			
			  for (int i = 0; i < timeline.size(); i++) 
				  System.out.print(timeline.get(i)+"  "); 
			      System.out.println();
			  for (int i = 0; i < output.size(); i++)
			      System.out.print(output.get(i).getName() + "  ");
			  System.out.println();
			  
			 
		}
		
		

	}


