package Simulation.Model.Process;

import java.util.ArrayList;
import java.util.List;

public class ProcessManager {

	private static List<Process> processes = new ArrayList<Process>();

	
	public static boolean CanFire()
	{
		for(Process process : processes)
		{
			if(process.CanFire())
			{
				return true;
			}
		}
		
		// If no process can fire return false
		return false;
	}
	
	public static void Fire()
	{
		for(Process process : processes)
		{
			if(process.CanFire())
			{
				process.Fire();
			}
		}
	}
	
	public static void AddProcess(Process[] processesToAdd)
	{
		for(Process proc : processesToAdd)
		{
			processes.add(proc);
		}
	}
	
}
