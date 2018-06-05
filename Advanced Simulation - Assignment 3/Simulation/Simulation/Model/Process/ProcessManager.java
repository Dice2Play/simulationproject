package Simulation.Model.Process;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Simulation.Enums.Resource_Type;
import Simulation.Model.Time.TimeManager;

public class ProcessManager {

	private static LinkedList<Process> processes = new LinkedList<Process>();
	private static Process currentRunningProcess;
	
	private static Process getCurrentRunningProcess() throws Exception
	{
		if(currentRunningProcess == null)
		{
			getNextProcessToRun();
		}
		
		return currentRunningProcess;
	}
	
	public static boolean CanFire() throws Exception
	{
		return getCurrentRunningProcess().CanFire();
	}
	
	public static void Fire() throws Exception
	{
		
		// Check if current process is finished
		// If so, get the next process
		if(getCurrentRunningProcess().isFinished()) 
		{ 
			// Notify that current process is finished
			System.out.println(String.format("PROCESS MANAGER: Process %s finished at %s", getCurrentRunningProcess().ID, TimeManager.GetTimeUnitsPassed()));
			
			// Reset process
			getCurrentRunningProcess().Reset();
			
			// Get next process
			getNextProcessToRun();
			
			// Start next process
			getCurrentRunningProcess().Start();
		}
		
		// Fire current process
		if(getCurrentRunningProcess().CanFire())
		{	
			getCurrentRunningProcess().Fire();
			System.out.println(String.format("PROCESS MANAGER: Process %s fired at %s", getCurrentRunningProcess().ID, TimeManager.GetTimeUnitsPassed()));	
		}

		
		
	}
	
	private static void getNextProcessToRun() throws Exception
	{
		// Check if empty, if so throw exception
		if(processes.isEmpty()) { throw new Exception("Their are no processes stored...");}
		
		// If there isn't any process currently running, get the first occurrence from list 'Processes'
		if(currentRunningProcess == null) { currentRunningProcess = processes.getFirst(); return;}
		
		// If there is a process currently running then,
		// get index of last element in 'processes' list,
		// if this index is out of bounds, return the first occurrence from the list 'Processes'
		int indexOfCurrentRunningProcess = processes.indexOf(currentRunningProcess);
		
		if((indexOfCurrentRunningProcess + 2) > processes.size()) { currentRunningProcess =  processes.getFirst();}
		else currentRunningProcess =  processes.get(indexOfCurrentRunningProcess + 1);
	}
	
	public static void AddProcess(Process process)
	{
			processes.add(process);
	}
	
	public static void Reset()
	{
		processes = new LinkedList<Process>();
	}
	
}
