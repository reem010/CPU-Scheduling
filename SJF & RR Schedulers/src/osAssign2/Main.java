package osAssign2;

public class Main {

	public static void main(String[] args) {
		PreemativeSJF obj1 = new PreemativeSJF()  ;
		System.out.println("\t" + "Preemative shortest job first  ");
		//obj1.Schedule();
		RoundRob obj2 = new RoundRob();
		System.out.println("\t" + "Round Robin ");
		obj2.Schedule();

	}

}
