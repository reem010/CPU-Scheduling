package osAssign2;

public class CPU {
	    CPU() {
	        this.CurrentProcess = new Process();
	    }

	    public Process CurrentProcess;
	    public int Timer;

	    public void CurrentP(Process currentP) {
	        CurrentProcess = currentP;
	    }

	
}
