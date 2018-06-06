package Simulation.Model.Process;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Simulation.Enums.Resource_Type;
import Simulation.Model.Time.TimeManager;

public class ProcessManager {

	private static LinkedList<Process> processes = new LinkedList<Process>();
	private static Process currentRunningProcess;

	
	
	public static boolean CanFire() throws Exception
	{
		// If no currentRunningProcess is defined yet, get first
		if(currentRunningProcess == null)
		{
			currentRunningProcess = getNextProcessToRun();
			StartProcess(currentRunningProcess);
		}
		
		return currentRunningProcess.CanFire();
	}
	
	private static void StartProcess(Process processToStart)
	{
		System.out.println(String.format("PROCESS MANAGER: Process %s started at %s", processToStart.getID(), TimeManager.GetTimeUnitsPassed()));
		processToStart.Start();
	}
	
	public static void Fire() throws Exception
	{
		// If no currentRunningProcess is defined yet, get first
		if(currentRunningProcess == null)
		{
			currentRunningProcess = getNextProcessToRun();
			StartProcess(currentRunningProcess);
		}
		
		
		// Check if current process is finished
		// If so, get the next process
		if(currentRunningProcess.isFinished()) 
		{ 
			// Notify that current process is finished
			System.out.println(String.format("PROCESS MANAGER: Process %s finished at %s", currentRunningProcess.getID(), TimeManager.GetTimeUnitsPassed()));
			
			// Stop and Reset process
			currentRunningProcess.Stop();
			currentRunningProcess.Reset();
		}
		
		// If no process is running, get next process
		if(!currentRunningProcess.isRunning())
		{
			currentRunningProcess = getNextProcessToRun();
			
			// Start new process
			StartProcess(currentRunningProcess);
		}
		
		
		// Fire current process
		if(!currentRunningProcess.isFinished() && currentRunningProcess.CanFire())
		{	
			currentRunningProcess.Fire();
			System.out.println(String.format("PROCESS MANAGER: Process %s fired at %s", currentRunningProcess.getID(), TimeManager.GetTimeUnitsPassed()));	
		}

		
		
	}
	// Returns the next process to run
	private static Process getNextProcessToRun() throws Exception
	{
		// Check if empty, if so throw exception
		if(processes.isEmpty()) { throw new Exception("Their are no processes stored...");}
		
		
		// If there isn't any process currently running, get the first occurrence from list 'Processes'
		if(currentRunningProcess == null) { return processes.getFirst();}
		
		// If there is a process currently running then,
		// get index of last element in 'processes' list,
		// if this index is out of bounds, return the first occurrence from the list 'Processes'
		int indexOfCurrentRunningProcess = processes.indexOf(currentRunningProcess);
		
		if((indexOfCurrentRunningProcess + 2) > processes.size())
		{ 
			return processes.getFirst();
		}
		
		else
		{
			return  processes.get(indexOfCurrentRunningProcess + 1);
		}
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
