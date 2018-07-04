package simulation.process;

import java.util.ArrayList;
import java.util.List;

public class ProcessManager {

	static List<Process> processes = new ArrayList<Process>();
	
	public static void AddProcess(Process processToAdd)
	{
		processes.add(processToAdd);
	}

	public static boolean CanFire() {
		// TODO Auto-generated method stub
		return false;
	}

	public static void Fire() {
		// TODO Auto-generated method stub
		
	}
}
