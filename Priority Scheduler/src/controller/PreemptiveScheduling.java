package controller;

import model.GanttRecord;
import model.Process;
import model.ReadyQueue;

import java.util.ArrayList;

public class PreemptiveScheduling {
    private ArrayList<GanttRecord> gantt;
    private int currentTime;
    private int exeTime;
    private ReadyQueue readyQueue;
    private int timer;
    
    public PreemptiveScheduling(){
        gantt = new ArrayList<>();
        currentTime = 0;
        exeTime = 0;
        timer= 0;
        readyQueue = new ReadyQueue();
    }

    public ArrayList<GanttRecord> getGantt(ArrayList<Process> processes){
        
        currentTime = this.getFirstArrivingTime(processes);
        int in = currentTime ,out = currentTime;

        Process pro=this.getFirstArrivingProcess(processes);
        readyQueue.enqueue(pro);
        processes.remove(pro);
        
        ArrayList<Process> orderedByArrivingTime = this.orderProcessesByArrivingTime(processes);

        while(!readyQueue.isEmpty()){
            Process process = readyQueue.dequeue();

            if(orderedByArrivingTime.size() > 0) {
                for (int i = 0; i < orderedByArrivingTime.size(); i++) {
                    Process p = orderedByArrivingTime.get(i);
                 
                    
                    	
         
                    if (p.getArrivingTime() >= process.getArrivingTime()
                            && p.getArrivingTime() < (process.getBurstTime() + currentTime)
                            && p.getPriority() >= process.getPriority()) {
                        readyQueue.enqueue(p);
                        orderedByArrivingTime.remove(p);
                        i--;
                    }
                    else if (p.getArrivingTime() >= process.getArrivingTime()
                            && p.getArrivingTime() < (process.getBurstTime() + currentTime)
                            && p.getPriority() < process.getPriority()) {
                        in = currentTime;
                        currentTime = p.getArrivingTime();
                        process.reduceTime(currentTime - in);
                        timer+= (currentTime - in);
                        if(timer%10==0) {
                        	for(Process newProcess: readyQueue.queue) {
                        		newProcess.setPriority(newProcess.getPriority()-1);
                        	}
                        }
                        out = currentTime;
                        readyQueue.enqueue(process);
                        GanttRecord gR = new GanttRecord(in, out, process.getProcessID());
                        gantt.add(gR);

                        readyQueue.enqueue(p);
                        orderedByArrivingTime.remove(p);
                        i--;

                        break;
                    }
                    if (i == orderedByArrivingTime.size() - 1) {
                        in = currentTime;
                        currentTime += process.getBurstTime();
                        timer+=process.getBurstTime();
                        if(timer%10==0) {
                        	for(Process newProcess: readyQueue.queue) {
                        		newProcess.setPriority(newProcess.getPriority()-1);
                        	}
                        }
                        out = currentTime;
                        gantt.add(new GanttRecord(in, out, process.getProcessID()));
                        if(orderedByArrivingTime.size() > 0
                                && readyQueue.isEmpty()) {
                            readyQueue.enqueue(orderedByArrivingTime.get(0));
                        }
                    }
                }
            }
            else{
                in = currentTime;
                currentTime += process.getBurstTime();
                timer+=process.getBurstTime();
                if(timer%10==0) {
                	for(Process newProcess: readyQueue.queue) {
                		newProcess.setPriority(newProcess.getPriority()-1);
                	}
                }
                out = currentTime;
                gantt.add(new GanttRecord(in, out, process.getProcessID()));
            }
        }
        return gantt;
    }

    public static int getCompletionTime(Process p, ArrayList<GanttRecord> gantt) {
        int completionTime = 0;
        for(GanttRecord gR : gantt){
            if(gR.getProcessId() == p.getProcessID())
                completionTime = gR.getOutTime();
        }
        return completionTime;
    }

    public static int getTurnAroundTime(Process p, ArrayList<GanttRecord> gantt) {
        int completionTime = PreemptiveScheduling.getCompletionTime(p,gantt);
        return completionTime-p.getArrivingTime();
    }

    public static int getWaitingTime(Process p, ArrayList<GanttRecord> gantt) {
        int turnAroundTime = PreemptiveScheduling.getTurnAroundTime(p,gantt);
        return turnAroundTime-p.getBurstTime();
    } 

    private ArrayList<Process> orderProcessesByArrivingTime(ArrayList<Process> processes){
        ArrayList<Process> newProcesses = new ArrayList<>();
        while(processes.size() != 0) {
            Process p = this.getFirstArrivingProcess(processes);
            processes.remove(p);
            newProcesses.add(p);
        }
        return newProcesses;
    }

    private Process getFirstArrivingProcess(ArrayList<Process> processes){
        int min = Integer.MAX_VALUE;
        Process process = null;
        for(Process p : processes){
            if(p.getArrivingTime() < min){
                min = p.getArrivingTime();
                process = p;
            }
        }
        return process;
    }

    private ArrayList<Process> getFirstArrivingProcesses(ArrayList<Process> processes){
        int min = this.getFirstArrivingTime(processes);
        ArrayList<Process> processes1 = new ArrayList<>();
        for(Process p : processes){
            if(p.getArrivingTime() == min){
                processes1.add(p);
            }
        }
        return processes1;
    }

    private int getFirstArrivingTime(ArrayList<Process> processes){
        int min = Integer.MAX_VALUE;
        for(Process p : processes){
            if(p.getArrivingTime() < min){
                min = p.getArrivingTime();
            }
        }
        return min;
    }

}