package Simulation.Model.Process;

import java.util.ArrayList;
import java.util.List;

public class ProcessManager {

	private static List<Process> processes = new ArrayList<Process>();

	
	public static boolean CanFire()
	{
		return false;
	}
	
	public static void Fire()
	{
		//Fire all processes
	}
	
	public static void AddProcess(Process[] processesToAdd)
	{
		for(Process proc : processesToAdd)
		{
			processes.add(proc);
		}
	}
	
}
